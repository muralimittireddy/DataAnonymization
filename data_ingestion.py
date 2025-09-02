import psycopg2
from faker import Faker
import random
import os

# ------------------------------
# Database connection details
# ------------------------------
DB_HOST = os.getenv('DB_HOST', 'postgres')
DB_PORT = os.getenv('DB_PORT', 5432)
DB_NAME = os.getenv('DB_NAME', 'anonymization_db')
DB_USER = os.getenv('DB_USER', 'postgres')
DB_PASSWORD = os.getenv('DB_PASSWORD', 'password')

# ------------------------------
# Data generation settings
# ------------------------------
NUM_RECORDS = 100_000
BATCH_SIZE = 1000

fake = Faker()

# Define main tables per system
MAIN_TABLES = {
    'abc': ['abc_table1', 'abc_table2', 'abc_table3'],
    'xyz': ['xyz_table1', 'xyz_table2', 'xyz_table3', 'xyz_table4'],
    'def': ['def_table1', 'def_table2', 'def_table3'],
}

# ------------------------------
# Database helper functions
# ------------------------------
def get_db_connection():
    try:
        conn = psycopg2.connect(
            host=DB_HOST,
            port=DB_PORT,
            database=DB_NAME,
            user=DB_USER,
            password=DB_PASSWORD,
            connect_timeout=10
        )
        return conn
    except psycopg2.Error as e:
        print(f"[ERROR] Could not connect to database: {e}")
        exit(1)

def create_tables(cursor):
    print("[INFO] Creating tables if not exist...")

    # report_table
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS report_table (
            id SERIAL PRIMARY KEY,
            external_number INT,
            internal_number INT,
            system VARCHAR(255) NOT NULL,
            isabc CHAR(1) DEFAULT 'N',
            isxyz CHAR(1) DEFAULT 'N',
            isdef CHAR(1) DEFAULT 'N',
            status VARCHAR(50) DEFAULT 'PENDING' NOT NULL
        );
    """)

    # ABC tables
    for i in range(1, 4):
        cursor.execute(f"""
            CREATE TABLE IF NOT EXISTS abc_table{i} (
                id SERIAL PRIMARY KEY,
                external_number INT NOT NULL,
                internal_number INT,
                name VARCHAR(255),
                email VARCHAR(255),
                phone VARCHAR(20),
                system_id INT
            );
        """)

    # XYZ tables
    for i in range(1, 5):
        cursor.execute(f"""
            CREATE TABLE IF NOT EXISTS xyz_table{i} (
                id SERIAL PRIMARY KEY,
                external_number INT NOT NULL,
                internal_number INT,
                name VARCHAR(255),
                email VARCHAR(255),
                phone VARCHAR(20),
                system_id INT
            );
        """)

    # DEF tables
    for i in range(1, 4):
        cursor.execute(f"""
            CREATE TABLE IF NOT EXISTS def_table{i} (
                id SERIAL PRIMARY KEY,
                external_number INT NOT NULL,
                internal_number INT,
                name VARCHAR(255),
                email VARCHAR(255),
                phone VARCHAR(20),
                system_id INT
            );
        """)

    print("[INFO] Tables created successfully.")

def clear_existing_data(cursor):
    print("[INFO] Clearing existing data...")
    for system_tables in MAIN_TABLES.values():
        for table in system_tables:
            cursor.execute(f"TRUNCATE TABLE {table} RESTART IDENTITY CASCADE;")
    cursor.execute("TRUNCATE TABLE report_table RESTART IDENTITY CASCADE;")
    print("[INFO] Data cleared successfully.")

# ------------------------------
# Data generation functions
# ------------------------------
def generate_report_entry(external_number):
    isabc = random.choices(['Y', 'N'], weights=[0.6, 0.4])[0]
    isxyz = random.choices(['Y', 'N'], weights=[0.5, 0.5])[0]
    isdef = random.choices(['Y', 'N'], weights=[0.4, 0.6])[0]

    system_options = [s for s, flag in zip(['ABC', 'XYZ', 'DEF'], [isabc, isxyz, isdef]) if flag == 'Y']
    if not system_options:
        system_options.append(random.choice(['ABC', 'XYZ', 'DEF']))
    system = random.choice(system_options)

    internal_number = fake.unique.random_int(min=1_000_000, max=9_999_999)
    return {
        'external_number': external_number,
        'internal_number': internal_number,
        'system': system,
        'isabc': isabc,
        'isxyz': isxyz,
        'isdef': isdef,
        'status': 'PENDING'
    }

def generate_main_table_entries(report_entry):
    entries = []
    for system_prefix, tables in MAIN_TABLES.items():
        if report_entry[f'is{system_prefix}'] == 'Y':
            table_name = random.choice(tables)
            entry = {
                'table': table_name,
                'external_number': report_entry['external_number'],
                'internal_number': fake.random_int(min=100_000, max=999_999),
                'name': fake.name(),
                'email': fake.email(),
                'phone': fake.phone_number(),
                'system_id': report_entry['external_number']
            }
            entries.append(entry)
    return entries

# ------------------------------
# Main script
# ------------------------------
def main():
    conn = get_db_connection()
    cursor = conn.cursor()

    try:
        create_tables(cursor)
        conn.commit()
        clear_existing_data(cursor)

        report_batch = []
        main_batch = []

        print(f"[INFO] Generating {NUM_RECORDS} records...")
        for i in range(1, NUM_RECORDS + 1):
            report_entry = generate_report_entry(i)
            report_batch.append(report_entry)

            main_entries = generate_main_table_entries(report_entry)
            main_batch.extend(main_entries)

            if i % BATCH_SIZE == 0:
                # Insert report_table batch
                report_values = [
                    (r['external_number'], r['internal_number'], r['system'],
                     r['isabc'], r['isxyz'], r['isdef'], r['status'])
                    for r in report_batch
                ]
                cursor.executemany(
                    "INSERT INTO report_table (external_number, internal_number, system, isabc, isxyz, isdef, status) "
                    "VALUES (%s,%s,%s,%s,%s,%s,%s)", report_values
                )
                report_batch.clear()

                # Insert main table batch
                table_grouped = {}
                for entry in main_batch:
                    table_grouped.setdefault(entry['table'], []).append(entry)
                for table, entries in table_grouped.items():
                    cursor.executemany(
                        f"INSERT INTO {table} (external_number, internal_number, name, email, phone, system_id) "
                        "VALUES (%s,%s,%s,%s,%s,%s)",
                        [(e['external_number'], e['internal_number'], e['name'], e['email'], e['phone'], e['system_id']) for e in entries]
                    )
                main_batch.clear()
                conn.commit()
                print(f"[INFO] Generated {i}/{NUM_RECORDS} records...")

        # Insert remaining records
        if report_batch:
            cursor.executemany(
                "INSERT INTO report_table (external_number, internal_number, system, isabc, isxyz, isdef, status) "
                "VALUES (%s,%s,%s,%s,%s,%s,%s)",
                [(r['external_number'], r['internal_number'], r['system'], r['isabc'], r['isxyz'], r['isdef'], r['status']) for r in report_batch]
            )
        if main_batch:
            table_grouped = {}
            for entry in main_batch:
                table_grouped.setdefault(entry['table'], []).append(entry)
            for table, entries in table_grouped.items():
                cursor.executemany(
                    f"INSERT INTO {table} (external_number, internal_number, name, email, phone, system_id) "
                    "VALUES (%s,%s,%s,%s,%s,%s)",
                    [(e['external_number'], e['internal_number'], e['name'], e['email'], e['phone'], e['system_id']) for e in entries]
                )

        conn.commit()
        print(f"[INFO] Successfully generated {NUM_RECORDS} records and main table entries.")

    except psycopg2.Error as e:
        conn.rollback()
        print(f"[ERROR] Data generation failed: {e}")
    finally:
        cursor.close()
        conn.close()

if __name__ == "__main__":
    main()
