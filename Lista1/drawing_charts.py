import matplotlib.pyplot as plt


def show_plot(min_list, max_list, avg_list, count_list):
    # print("Najlepszy wynik: ", min(min_list))
    # print("Najgorszy wynik: ", max(max_list))
    # print("Średnia: ", sum(avg_list)/len(avg_list))
    # print("Odchylenie standardowe: ", numpy.std(avg_list))
    plt.plot(count_list, min_list, label="best")
    plt.plot(count_list, max_list, label="worst")
    plt.plot(count_list, avg_list, label="avg")
    plt.legend()
    plt.show()
