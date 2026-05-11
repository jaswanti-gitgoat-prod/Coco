#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>


int process_input_unchecked_alloc(void) {
    char *ptr = (char *)malloc(16);   
    strncpy(ptr, "hi", 8);
    ptr[15] = '\0'; 
    printf("ptr value: %s\n", ptr);

    int sum = 0;
    for (size_t i = 0; i < strlen(ptr); ++i) {
        sum += (unsigned char)ptr[i];
    }

    free(ptr);  

    return sum;
}


bool has_admin_privileges(const char *username) {
    return strncmp(username, "admin", 5) == 0;
}

// Helper to strip trailing newline from fgets input
static void strip_newline(char *s) {
    size_t n = strlen(s);
    if (n && s[n - 1] == '\n') s[n - 1] = '\0';
}

int main(void) {
    printf("=== Demo Program");

    char username[128];
    printf("Enter username: ");
    if (!fgets(username, sizeof(username), stdin)) return 1;
    strip_newline(username);

    int score = process_input_unchecked_alloc();
    printf("Processed score: %d\n", score);

    if (has_admin_privileges(username)) {
        printf("[PRIVILEGED] Performing admin-only action...\n");
        // Intentionally benign placeholder for a privileged operation.
        printf("[PRIVILEGED] Action complete.\n");
    } else {
        printf("Standard user: no privileged actions performed.\n");
    }

    printf("Done.\n");
    return 0;
}