def get_words_count_dct(reviews):
    words_count_in_reviews_dct = {}
    for review in reviews:
        words_count_dct = get_words_count(review)
        for word in words_count_dct.items():
            if word[0] in words_count_in_reviews_dct:
                words_count_in_reviews_dct[word[0]] += word[1]
            else:
                words_count_in_reviews_dct[word[0]] = word[1]
    return {k: v for k, v in sorted(words_count_in_reviews_dct.items(), key=lambda item: item[1], reverse=True)}


def get_words_count(text: str):
    words_count_dct = {}
    words = text.split()
    for word in words:
        if word in words_count_dct:
            words_count_dct[word] += 1
        elif is_word(word):
            words_count_dct[word] = 1
    return words_count_dct


def is_word(text: str):
    for i in range(len(text)):
        if text[i].upper() < 'A' or text[i].upper() > 'Z':
            if text[i] != "'" and text[i] != '-' and text[i] != '/':
                return False
    return True

