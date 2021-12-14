#include <iostream>
#include <conio.h> 
#include <windows.h>
#include <io.h>
#include <string>
#include <fstream>


void Show_Commands() { //Все команды редактора
    system("Cls");
    std::cout << "This is list of commands of editor" << std::endl;
    std::cout << "ctrl+n                   Show this list" << std::endl;
    std::cout << "ctrl+p                   Create a file" << std::endl;
    std::cout << "ctrl+o                   Open a file" << std::endl;
}






bool check_file(const std::string name) { //Проверка создан ли такой файл уже, если тру - создан.
    std::ifstream file(name);
    if (file.is_open()) {
        return true;
    }
    else {
        return false;
    }


}


void open_file(std::string name) { //Открывает существующий файл

    while (!check_file(name)) {
        system("Cls");
        std::cout << "There are no files with this name.Try entering a different file name: ";
        std::cin >> name;
    }
    if (check_file(name)) {
        std::ifstream fin(name);
        system("Cls");
        std::cout << "File " << name << " was opened";
    }

}


void create_file(std::string name) { //Создает новый файл

    std::ofstream fout;
    while (check_file(name)) {
        system("Cls");
        std::cout << "A file with that name already exists.Try entering a different file name: ";
        std::cin >> name;
    }
    if (!check_file(name)) {
        fout.open(name);
        system("Cls");
        std::cout << "File " << name << " was created successfully";
    }
}


void location_x_y(int x, int y)
{
    static HANDLE h = NULL;
    if (!h) {
        h = GetStdHandle(STD_OUTPUT_HANDLE);
    }
    COORD c = { x, y };
    SetConsoleCursorPosition(h, c);
}



int main()
{


    int Keys;
    int poz_x = 0;
    int poz_y = 0;
    location_x_y(poz_x, poz_y);



    Show_Commands(); //Вызывает командную строку
    Keys = _getch();
    while (Keys != 27) {
        switch (Keys) {

        case 16: {
                std::string name;
                std::cout << "Enter the file name in format 'name.txt': "; //Если ctrl+p, то вызывает функцию создания нового файла
                std::cin >> name;
                create_file(name);
                break;
        }

        case 15: {
                std::string name;
                std::cout << "Enter the file name in format 'name.txt': "; //Если ctrl+o, то вызывает функцию открытия существ. файла
                std::cin >> name;
                open_file(name);
                break;
        }
        case 14: {
            Show_Commands(); //Если ctrl+n вызывает командную строку
            break;
        }
        }


        Keys = _getch();
        if (Keys == 77) {

            poz_x += 1;
            location_x_y(poz_x, poz_y);

        }
        if (Keys == 75) {

            poz_x -= 1;
            location_x_y(poz_x, poz_y);

        }
        if (Keys == 80) {

            poz_y += 1;
            location_x_y(poz_x, poz_y);

        }
        if (Keys == 72) {

            poz_y -= 1;
            location_x_y(poz_x, poz_y);

        }
        if (Keys == 13) {

            poz_y += 1;
            poz_x = 0;
            location_x_y(poz_x, poz_y);

        }
        if (poz_x < 0) {

            poz_x = 0;

        }
        if (poz_y < 0) {

            poz_y = 0;

        }
    }
    return 0;
}