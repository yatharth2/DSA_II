#include <iostream>
#include <chrono>
#include <thread>
#include <iomanip> 

using namespace std;
using namespace std::chrono;

int main() {
    const int MAX = 1000; 
    int a[MAX];
    
    using double_ms = duration<double, milli>;

    auto t1 = high_resolution_clock::now();

    for(int i = 0; i < MAX; i++) {
        a[i] = i;
        
        this_thread::sleep_for(microseconds(3002));
    }

    auto t2 = high_resolution_clock::now();

    double_ms dt = t2 - t1; 

    cout << fixed << setprecision(3);
    cout << "Time taken: " << dt.count() << " milliseconds" << endl;
    
    return 0;
}