#include <err.h>
#include <stdio.h>
#include <string.h>

#include "mbus-protocol-aux.h"

int main(int argc, char *argv[])
{
    FILE *fp = NULL;
    size_t buff_len;
    int result, normalized = 0;
    unsigned char raw_buff[4096], buff[4096];
    char *xml_result = NULL, *file = NULL;

    if (argc == 3 && strcmp(argv[1], "-n") == 0)
    {
        file = argv[2];
        normalized = 1;
    }
    else if (argc == 2)
    {
        file = argv[1];
    }
    else
    {
        fprintf(stderr, "usage: %s [-n] hex-file\n", argv[0]);
        fprintf(stderr, "    optional flag -n for normalized values\n");
        return 1;
    }

    if ((fp = fopen(file, "r")) == NULL)
    {
        fprintf(stderr, "%s: failed to open '%s'\n", argv[0], file);
        return 1;
    }

    memset(raw_buff, 0, sizeof(raw_buff));
    fread(raw_buff, 1, sizeof(raw_buff), fp);

    if (ferror(fp) != 0)
    {
        fprintf(stderr, "%s: failed to read '%s'\n", argv[0], file);
        fclose(fp);
        return 1;
    }

    fclose(fp);

    buff_len = mbus_hex2bin(buff, sizeof(buff), raw_buff, sizeof(raw_buff));

    printf("DONE 98765");

    return 0;
}
