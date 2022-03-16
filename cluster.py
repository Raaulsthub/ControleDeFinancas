import mysql.connector
import textdistance as dist

def substringMethod(substring, product):
    return substring in product

def fullStringLevensheteinMehod(product1, product2):
    sim = (1 - dist.levenshtein(product1, product2)/min(len(product1), len(product2)) * 100)
    return sim > 50

def wordsLevenshteinMethod(substring, product2):
    listed_product = product2.split(' ')
    for word in listed_product:
        if (1 - dist.levenshtein(substring, word)/min(len(substring), len(word))) * 100 > 90:
            return True
    return False

def fullStringJaroWinklerMethod(product1, product2):
    return dist.jaro_winkler(product1, product2, 0.8) > 0.7

def wordsJaroWinklerMethod(substring, product2):
    listed_product = product2.split(' ')
    for word in listed_product:
        if dist.jaro_winkler(substring, word) > 0.7:
            return True
    return False


#mysql conn

mydb = mysql.connector.connect(host="localhost", user="root", password="raulstein22#!A", database="javaapp")
mycursor = mydb.cursor()


#getting a list with all the products

mycursor.execute("select descricao from produto where idprod")

values = list(mycursor)

#CLUSTERING

listOfLists = []

while values:

    # creating core
    core = []

    word = str(values[0])
    core.append(word)
    values.pop(0)

    word = word.replace("(", "")
    word = word.replace(")", "")
    word = word.replace(",", "")
    word = word.replace("'", "")

    wordListed = word.split(' ')

    reference = wordListed[0]

    if len(reference) < 3:
        values.pop(0)
        core.append(word)
        continue


    for i in values:
        if substringMethod(reference, str(i)):
        #if fullStringLevensheteinMehod(word, str(i)):
        #if fullStringJaroWinklerMethod(word, str(i)):
        #if wordsLevenshteinMethod(reference, str(i)):
        #if wordsJaroWinklerMethod(reference, str(i)):
            core.append(i)
            values.remove(i)

    listOfLists.append(core)

    print(reference.upper())

    for i in core:
        print(i)
    print("-----------------------\n" * 3)



