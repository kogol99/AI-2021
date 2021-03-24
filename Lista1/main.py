import numpy

from drawing_charts import show_plot
from operations import run_algorithm

POPULATION_SIZE = 5


def print_list(list):
    for i in range(len(list)):
        print(list[i])


if __name__ == '__main__':
    # test.print_info_about_path()
    # test.print_board()
    the_best, min_list, max_list, avg_list, count_list = run_algorithm("tournament")
    # print(the_best.score)
    # the_best.print_board()
    # the_best.print_pcb_solution()
    max_amount = []
    min_amount = []
    avg_amount = []
    std_amount = []
    for i in range(3):
        the_best, min_list, max_list, avg_list, count_list = run_algorithm("tournament")
        max_amount.append(max(max_list))
        min_amount.append(min(min_list))
        avg_amount.append(round(sum(avg_list)/len(avg_list), 2))
        std_amount.append(round(numpy.std(avg_list), 2))
        show_plot(min_list, max_list, avg_list, count_list)
    print("Max value")
    print_list(max_amount)
    print("Min value")
    print_list(min_amount)
    print("Avg value")
    print_list(avg_amount)
    print("Std value")
    print_list(std_amount)



