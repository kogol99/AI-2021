import transforming_data
from data_reader import get_file_data
from analyzer import get_words_count_dct
from exploratory_analysis import print_least_used_words, print_most_used_words, print_all_words, \
    print_typical_review_statistic, print_occurrence_of_classes


def zad1():
    reviews, rates = get_file_data("Dennis+Schwartz")
    words_count_dct = get_words_count_dct(reviews)
    # print_all_words(words_count_dct)
    print_least_used_words(words_count_dct)
    print_most_used_words(words_count_dct)
    print()
    print_typical_review_statistic(reviews)
    print()
    print_occurrence_of_classes(rates)


if __name__ == '__main__':
    zad1()
