def read_file(file):
    global n
    f = open(file, 'r', encoding='utf-8')
    n = int(f.readline())
    points = []
    graph = []
    for i in range(n):
        inp = f.readline().split()
        points.append((int(inp[0]), int(inp[1])))
        graph.append([0]*n)
    f.close()
    return create_graph(graph, points)

def create_graph(graph, point) -> [[int]]:
    for i in range(len(point)):
        for j in range(len(point)):
            graph[i][j] = abs(point[i][0] - point[j][0]) + abs(point[i][1] - point[j][1])
    weights = []
    for i in range(len(point)):
        for j in range(len(point) - i):
            if graph[i][j + i] != 0:
                weights.append(((i, j + i), graph[i][j + i]))
    return weights

def kruskal(graph):
    Comp = [i for i in range(n)]
    Ans = 0
    res = []
    for i in range(n):
        res.append([])
    for point, weight in graph:
        if Comp[point[0]] != Comp[point[1]]:
            Ans += weight
            a = Comp[point[0]]
            b = Comp[point[1]]
            res[point[0]].append(point[1] +1)
            res[point[1]].append(point[0] + 1)
            for i in range(n):
                if Comp[i] == b:
                    Comp[i] = a
    return (Ans, res)

def write_file(file, data, sum):
    out = ""
    f = open(file, 'w', encoding='utf-8')
    point_number = 1
    for i in data:
        out += str(point_number) + ': '
        point_number += 1
        i.sort()
        for j in i:
            out += str(j) + " "
        out += "0\n"
    out += str(sum)
    f.write(out)
    f.close()

if __name__ == '__main__':
    weights = read_file("in.txt")
    weights.sort(key=lambda point: point[1])
    sum, array = kruskal(weights)
    write_file("out.txt", array, sum)
