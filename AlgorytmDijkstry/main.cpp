#include <iostream>
#include <math.h>
#include <limits.h>
using namespace std;

struct punkt
{
    char n;
    int x;
    int y;

};

punkt wczytajPunkt()
{
    punkt* p = new punkt;
    cin >> p->n >> p->x >> p->y;
    return *p;
}

double odleglosc (punkt p1,punkt p2);

int main()
{
    int n;
    cin >> n;
    char nazwa [n];
    double odl;

    bool odwiedzony[n];

    for (int i = 0; i < n; i++)
    {
        odwiedzony[i] = false;
    }

    struct punkt* punkty = new punkt[n];
    for (int i = 0; i < n; i++)
    {

        punkty[i] = wczytajPunkt();
    }

    int start,nowystart;
    double suma=0;
    cin >> start;
    start--;
    int str= start;
    odwiedzony[str] = true;
    cout << "NAjkrotsza droga prowadzi przez punkty: " << punkty[str].n << " -> ";
    for(int i =0; i<(n-1); i++)
    {

        double mini = INT_MAX;
        for(int j= 0; j<n; j++)
        {

            if(j!=(start) && odwiedzony[j]==false)
            {

                odl=odleglosc(punkty[start],punkty[j]);

                if (odl< mini)
                {
                    mini=odl;
                    nowystart=j;
                }

            }

        }
        start=nowystart;
        suma+=mini;
        odwiedzony[start]=true;
        cout << punkty[start].n << " -> ";

    }
    suma+=odleglosc(punkty[start], punkty[str]);
    cout << endl << "Dlugość drogi wynosi: " << suma;

    return 0;
}

double odleglosc (punkt p1,punkt p2)
{

    return sqrt(pow((p1.x - p2.x), 2) + pow((p1.y - p2.y), 2));

}


