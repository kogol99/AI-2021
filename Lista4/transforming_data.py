def get_review_from_data(data):
    reviews = []
    for data_line in data:
        reviews.append(data_line[0])
    return reviews
