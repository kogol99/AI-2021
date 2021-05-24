from gensim.parsing.preprocessing import remove_stopwords

def get_file_data(file_extension):
    rates = __get_data_from_one_file("rating", file_extension)
    reviews = __get_data_from_one_file("subj", file_extension)
    return reviews, rates


def __get_data_from_one_file(data_type, name):
    data = []
    with open(f'data/{name}/{data_type}.{name}', 'r') as f:
        for line in f:
            line = line.replace("\n", "")
            line = remove_stopwords(line)
            data.append(line)
    return data


def get_representation_data(reviews, rates):
    representation_data = []
    for i in range(len(reviews)):
        representation_data.append([rates[i], reviews[i]])
    return representation_data
