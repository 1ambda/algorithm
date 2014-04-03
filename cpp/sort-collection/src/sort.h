#include <iostream>
#include <functional>

using namespace std;

template <typename T>
class Sort
{
public:
    Sort<T>();
    virtual ~Sort<T>();

    static bool bubble(T* arr, size_t length, function<bool(T& t1, T& t2)>& comparator);
    static bool selection(T* arr, size_t length, function<bool(T& l, T& r)>& comparator);
    static bool insertion(T* arr, size_t length, function<bool(T& l, T& r)>& comparator);
    static bool merge(T* arr, int length, function<bool(T&, T&)>& comparator);
    static bool quick(T* arr, int length, function<bool(T&, T&)>& comparator);

private:
    static void internal_merge(T* arr, int length, function<bool(T&, T&)>& comparator);
    static void internal_quick(T* arr, int first, int last, function<bool(T&, T&)>& comparator);
};

template <typename T>
Sort<T>::Sort() {
    
}

template <typename T>
Sort<T>::~Sort() {
    
}

template <typename T>
bool Sort<T>::bubble(T* arr, size_t length, function<bool(T&, T&)>& comparator) {

    if ( arr == nullptr  || length <= 1) {
        return false;
    }

    bool sorted;
    
    for(int i = length; i >1; i--) {
        sorted = true;
        
        for(int j = 0; j < i - 1; j++) {
            if (comparator(arr[j], arr[j+1])) {
                T* temp = new T(arr[j]);
                arr[j] = arr[j+1];
                arr[j+1] = *temp;
                sorted = false;
                delete temp;
            }
        }

        if (sorted) {
            break;
        }
    }

    return true;
}

template <typename T>
bool Sort<T>::selection(T* arr, size_t length, function<bool(T&, T&)>& comparator) {

    if(arr == nullptr) {
        return false;
    }

    if (length <= 1) {
        return false;
    }

    for(int i = 0; i < length - 1; i++) {
        for(int j = i+1; j < length; j++) {
            if( comparator(arr[i], arr[j]) ) {
                T* temp = new T(arr[j]);
                arr[j] = arr[i];
                arr[i] = *temp;
                delete temp;
            }
        }
    }

    return true;
}

template <typename T>
bool Sort<T>::insertion(T* arr, size_t length, function<bool(T&, T&)>& comparator) {

    if(arr == nullptr) {
        return false;
    }

    if(length <= 1) {
        return false;
    }

    int j = 0;

    for(int i = 1; i < length; i++) {
        T* temp = new T(arr[i]);

        for(j = i - 1; j >= 0 && comparator(arr[j], *temp); j--)
            arr[j+1] = arr[j];

        arr[j+1] = *temp;
        delete temp;
    }

    return true;
}

template <typename T>
bool Sort<T>::quick(T* arr, int length, function<bool(T&, T&)>& comparator) {
    if(arr == nullptr) {
        return false;;
    }

    if (length <= 1) {
        return false;
    }

    Sort<T>::internal_quick(arr, 0, length - 1, comparator);
    
    return true;
}

// ref : http://www.algolist.net/Algorithms/Sorting/Quicksort
template <typename T>
void Sort<T>::internal_quick(T* arr, int left, int right, function<bool(T&, T&)>& f) {

    int i = left;
    int j = right;
    int pivot = (left + right) / 2;

    while (i <= j) {
        while(f(arr[pivot], arr[i])) i++;
        while(f(arr[j], arr[pivot])) j--;

        if (i <= j) {
            T* temp = new T(arr[j]);
            arr[j] = arr[i];
            arr[i] = *temp;
            delete temp;
            i++; j--;
        }
    }

    if (left < j) {
        internal_quick(arr, left, j, f);
    }

    if (i < right) {
        internal_quick(arr, i, right, f);
    }
}

template <typename T>
bool Sort<T>::merge(T* arr, int length, function<bool(T&, T&)>& comparator) {

    if(arr == nullptr) {
        return false;;
    }

    if (length <= 1) {
        return false;
    }

    Sort<T>::internal_merge(arr, length, comparator);
    
    return true;
}

template <typename T>
void Sort<T>::internal_merge(T* arr, int length, function<bool(T&, T&)>& f) {

    if ( length > 1) {

        int mid = length / 2;

        internal_merge(arr, mid, f);
        internal_merge(arr + mid, length - mid, f);

        // merge two partial arrays
        T* temp = new T[length];

        int left, right, index;
        for(index = 0, left = 0, right = mid; left < mid && right < length; index++) {
            temp[index] = (f(arr[left], arr[right])) ? arr[right++] : arr[left++];
        }

        while(left < mid) temp[index++] = arr[left++];
        while(right < length) temp[index++] = arr[right++];

        for(index = 0; index < length; index++) {
            arr[index] = temp[index];
        }

        delete temp;
    }
}



