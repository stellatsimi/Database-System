import math
import random

records = math.floor(60_000)
number_of_offers = 3

if records % number_of_offers != 0:
    records = records + 3 - (records % number_of_offers)

with open("names.txt", "r") as f:
    name = []
    surname = []
    offer_code = range(1, 4)
    prepay = range(50, 201)
    for line in f:
        name = name + [line.split("\t")[0]]
        surname = surname + [line.split("\t")[1].replace('\n', '')]

    mydict = {}
    for i in range(number_of_offers):
        mydict[str(i + 1)] = records // number_of_offers

    with open("code/SQL scripts/insertData.sql", "a") as f2:
        f2.write("\n\n-- reservation_offers table\n")
        randomName = random.sample(name, k=1)[0]
        randomSurname = random.sample(surname, k=1)[0]

        randomTrip = random.randint(1, number_of_offers)
        while mydict[str(randomTrip)] == 0:
            randomTrip = random.randint(1, number_of_offers)
        else:
            mydict[str(randomTrip)] -= 1

        randomDeposit = round(random.uniform(50, 200), 2)
        sqlQuery = f'INSERT INTO reservation_offers VALUES (NULL, "{randomSurname}", "{randomName}", {randomTrip}, {randomDeposit})'
        f2.write(sqlQuery)
        for i in range(records-1):
            randomName = random.sample(name, k=1)[0]
            randomSurname = random.sample(surname, k=1)[0]

            randomTrip = random.randint(1, number_of_offers)
            while mydict[str(randomTrip)] == 0:
                randomTrip = random.randint(1, number_of_offers)
            else:
                mydict[str(randomTrip)] -= 1

            randomDeposit = round(random.uniform(50, 200), 2)
            sqlQuery = f',\n\t\t\t\t\t\t\t\t\t  (NULL, "{randomSurname}", "{randomName}", {randomTrip}, {randomDeposit})'
            f2.write(sqlQuery)
        f2.write(";\n")

print(f"finished ({i+1+1})")
