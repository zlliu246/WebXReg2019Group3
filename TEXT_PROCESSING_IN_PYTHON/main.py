import pandas as pd
import re
import nltk
from string import punctuation
from nltk.corpus import stopwords
from nltk.tokenize import RegexpTokenizer
from nltk.stem import WordNetLemmatizer
from nltk.stem.porter import PorterStemmer

stopwords = set(stopwords.words("english"))
tokenizer = RegexpTokenizer(r"\w+")
lem = WordNetLemmatizer()

def remove_punctuation(string):
    return "".join([i for i in string if i not in punctuation])

def tokenize(string):
    return tokenizer.tokenize(string)

def remove_stopwords(lis):
    return [i for i in lis if i not in stopwords]

"""
input: sentence
output: processed list of words
"""
def clean(string):
    string = str(string).lower()
    list_words = remove_stopwords(tokenize(remove_punctuation(string)))
    return " ".join([lem.lemmatize(i) for i in list_words])

#retrieve all data from the csv file in DataFrame

data = pd.read_csv("from.csv")

#retrieve data within the 'paragraph' column and execute clean() function on each

data["paragraph"] = [clean(i) for i in data["paragraph"]]

#populate all the new data into another csv file

data.to_csv("out.csv", index=False)

print("Complete Cleaning")

