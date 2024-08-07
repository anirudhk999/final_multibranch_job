import os
import matplotlib.pyplot as plt


def extract_stats(file):
    with open(file, 'r') as f:
        return [float(line.strip()) for line in f]

def plotgraph(stats):
    if os.path.exists('C:\\DEVOPS_ASSESMENT\\GRAPH\\stats.png'):
        os.remove('C:\\DEVOPS_ASSESMENT\\GRAPH\\stats.png')
    algos = ['bubble', 'insertion', 'merge', 'selection']
    plt.figure(figsize=(10, 10))
    count = 0
    for key, value in stats.items():
        plt.plot(value, label=algos[count])
        count += 1
    plt.legend()
    plt.xlabel('Array Size')
    plt.ylabel('Time')
    plt.title('STATS')
    plt.savefig('C:\\DEVOPS_ASSESMENT\\GRAPH\\stats.png')

def main():
    FILES_DIR = 'C:\\DEVOPS_ASSESMENT\\GRAPH'
    count = 1
    stats = {}
    for i in os.listdir(FILES_DIR):
        if i.endswith('.txt'):
            stats[count] = extract_stats(FILES_DIR + "\\" + i)
            count += 1
    plotgraph(stats)
    for i in os.listdir(FILES_DIR):
        if i.endswith('.txt'):
            os.remove(FILES_DIR + "\\" + i)

if __name__ == "__main__":
    main()