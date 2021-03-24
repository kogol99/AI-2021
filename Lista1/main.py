import sys
import numpy

from drawing_charts import show_plot
from operations import run_algorithm


if __name__ == '__main__':
    # test.print_info_about_path()
    # test.print_board()
    the_best, min_list, max_list, avg_list, count_list = run_algorithm("tournament")
    # print(the_best.score)
    # the_best.print_board()
    # the_best.print_pcb_solution()
    max_amount = []
    min_amount = []

    list_of_min_list = []
    list_of_max_list = []
    min_list_to_plot = []
    max_list_to_plot = []
    avg_list_to_plot = []

    for i in range(3):
        the_best, min_list, max_list, avg_list, count_list = run_algorithm("roulette")
        min_amount.append(min(min_list))
        max_amount.append(max(max_list))
        list_of_min_list.append(min_list)
        list_of_max_list.append(max_list)
    for i in range(len(count_list)):
        max_value = 0
        min_value = sys.maxsize
        sum_min_value = 0
        sum_max_value = 0
        for j in range(len(list_of_max_list)):
            sum_min_value += list_of_min_list[j][i]
            sum_max_value += list_of_max_list[j][i]
            if min_value > list_of_min_list[j][i]:
                min_value = list_of_min_list[j][i]
        min_list_to_plot.append(min_value)
        max_list_to_plot.append(sum_max_value/len(list_of_max_list))
        avg_list_to_plot.append(sum_min_value/len(list_of_min_list))

    show_plot(min_list_to_plot, max_list_to_plot, avg_list_to_plot, count_list)

    print("Min value")
    print(min(min_amount))
    print("Max value")
    print(max(max_amount))
    print("Avg value")
    print(round(sum(min_amount)/len(min_amount), 2))
    print("Std value")
    print(round(numpy.std(min_amount), 2))



