import sys

def main(args):
    bytes_list = []
    outfile = args[1]

    with open(args[0], 'r') as infile:
        for line in infile:
            bytes_list.append(int(line[0:2], 16))
            bytes_list.append(int(line[2:4], 16))

    with open(outfile, 'wb') as out:
        out.write(bytearray(bytes_list))

if __name__ == "__main__":
    main(sys.argv[1:])