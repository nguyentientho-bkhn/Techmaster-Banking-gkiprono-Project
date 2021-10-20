'''
Author:     Gabriel Kiprono
Purpose:    Help automate redundant insert statements for 
            creating users and initialisig their accounts           

'''


import psycopg2
from configparser import ConfigParser, Error
import pandas as pd

###############

###############
def getConfig(filename, section):
    parser = ConfigParser()
    
    parser.read(filename)


    db = {}

    if parser.has_section(section):
        params = parser.items(section)
        for para in params:
            db[para[0]] = para[1]

    else:
        raise Exception('Section {0} not found in the {1} file'.format(section, filename))

    return db


def getConnection(filename, section):
    connection = None

    try:
        parameters = getConfig(filename, section)
        connection = psycopg2.connect(**parameters)

        cursor = connection.cursor()

        print("Postgresql database version")
        cursor.execute('SELECT version()')

        version = cursor.fetchone()
        print(version)
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if connection is not None:
           return connection
#################################33
#initial customers
#
#################################

def readCsv(connection, filename):
    cursor = connection.cursor()
    count = 0
    data = pd.read_csv(filename)

    print(data.head(6))
    columns = ['cid','first','mi','last','phonenumber','addr','City','State','ZipCOde','accId']

    inserStatement = """
        INSERT INTO customers(
            userid, firstname,middleinitial,lastname,phonenumber,address,city,state,zipcode,accountnumber
        )VALUES(
            %s,%s,%s,%s,%s,%s,%s,%s,%s,%s
        )
    """

    for row in data.itertuples():
        try:
            cursor.execute(inserStatement, (
                row.cid, row.first,row.mi,row.last,row.phonenumber,row.addr,row.City,row.State,row.ZipCOde,row.accId
            ))
            connection.commit()
            count+=1
            print(count)
        except Error as e:
            print(f"Error '{e}' happened")
        except (Exception, psycopg2.DatabaseError) as e:
            print(f"Error '{e}' happened")

##########################
#initialize accounts with $100
#
##########################
def initAccounts(connection, filename):
    cursor = connection.cursor()
    count = 0
    data = pd.read_csv(filename)

    print(data.head(6))

    columns = ['accountId','runningBalance','accountNumber']

    insertStatement = """
        INSERT INTO accounts (accountId, runningBalance, accountNumber) 
        VALUES( %s,%s,%s)
    """

    for row in data.itertuples():
        try:
            cursor.execute(insertStatement, (
                row.accountId, row.runningBalance, row.accountNumber
            ))
            count+=1
            connection.commit()
            print(count)
        except Error as e:
            print(f"Error '{e}' happened")
        except (Exception, psycopg2.DatabaseError) as e:
            print(f"Error '{e}' happened")

def main():
    print("Start")
    file = "database.ini"
    section = "postgresql"
    connection = getConnection(filename=file, section=section)
    #readCsv(connection, filename="complete.csv")
    #initAccounts(connection=connection, filename="accounts.csv")

    connection.close()

if __name__ == "__main__":
    print("Here")
    main()