import sys

res = []

def prim(matr):
    selected_node = [0] * len(matr)
    N = len(matr)
    no_edge = 0
    sum = 0
    selected_node[0] = True
    while (no_edge < N - 1):
        minimum = sys.maxsize
        a = 0
        b = 0
        for m in range(N):
            if selected_node[m]:
                for n in range(N):
                    if ((not selected_node[n]) and matr[m][n] != -1):
                        if minimum > matr[m][n]:
                            minimum = matr[m][n]
                            a = m
                            b = n
        print(str(a) + "-" + str(b) + ":" + str(matr[a][b]))
        res[a][b] = res[b][a] = matr[a][b]
        sum += matr[a][b]
        selected_node[b] = True
        no_edge += 1
    print(sum)
    print(res)
    return [res, sum]

def read_file(file):
    global n
    f = open(file, 'r', encoding='utf-8')
    n = int(f.readline())
    print(n)
    graph = []
    for i in range(n):
        graph.append([])
        res.append([])
        inp = f.readline().split()
        for j in range(n):
            c = int(inp[j])
            if c == 32767:
                graph[i].append(-1)
            else:
                graph[i].append(c)
            res[i].append(-1)
    f.close()
    return graph

def write_file(file, data, summ):
    out = ""
    f = open(file, 'w', encoding='utf-8')
    for i in range(n):
        out += str(i + 1) + ': '
        for j in range(n):
            if data[i][j] != -1:
                out += str(j + 1) + ' '
        out += "0\n"
    out += str(summ)
    print(out)
    f.write(out)
    f.close()

if __name__ == '__main__':
    graph = read_file("in.txt")
    print(graph)
    prim = prim(graph)
    write_file("out.txt", prim[0], prim[1])