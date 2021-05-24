from analyzer import is_word

WORDS_TO_PRINT_AMOUNT = 10


def print_least_used_words(words_count_dct):
    print('\nLeast used words:')
    words_count_dct = {k: v for k, v in sorted(words_count_dct.items(), key=lambda item: item[1])}
    __print_words(words_count_dct)


def print_most_used_words(words_count_dct):
    print('\nMost used words:')
    __print_words(words_count_dct)


def __print_words(words_count_dct, words_amount=WORDS_TO_PRINT_AMOUNT):
    printed_words = 0
    for word_with_count in words_count_dct.items():
        print(f'{word_with_count[0]}: {word_with_count[1]}')
        printed_words += 1
        if printed_words == words_amount:
            break


def print_typical_review_statistic(reviews):
    reviews_amount = []
    for review in reviews:
        review_length = 0
        for word in review.split():
            if is_word(word):
                review_length += 1
        reviews_amount.append(review_length)

    average_length = sum(reviews_amount)/len(reviews_amount)
    print("Review average length [word]: ", average_length)


def print_all_words(words_count_dct):
    for item in words_count_dct.items():
        print(item[0] + " " + str(item[1]))


def print_occurrence_of_classes(rates):
    rates_amount = {}
    for rate in rates:
        if rate in rates_amount:
            rates_amount[rate] += 1
        else:
            rates_amount[rate] = 1
    rates_amount = {k: v for k, v in sorted(rates_amount.items(), key=lambda item: item[0], reverse=True)}
    print("The number of class occurrences")
    print_all_words(rates_amount)