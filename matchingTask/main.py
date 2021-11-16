def read_file(file):
    global size, k, l
    f = open(file, 'r', encoding='utf-8')
    a = f.readline()
    k = int(a.split('\n')[0].split()[0])
    l = int(a.split('\n')[0].split()[0])
    arrayAdjency = []
    size = int(f.readline().split('\n')[0])
    for i in range(size  // 10 + 1):
        inp = f.readline().split()
        inp = [int(x.rstrip()) for x in inp]
        for i in inp:
            arrayAdjency.append(i)
    f.close()
    arrayAdjency = arrayAdjency[0:len(arrayAdjency)- 1 ]
    print(arrayAdjency)
    return


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
    read_file('in.txt')
