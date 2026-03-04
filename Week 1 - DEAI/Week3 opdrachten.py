from abc import ABC, abstractmethod

# --- SORU 1: Temel Sınıfların Oluşturulması ---

class LinkedList(ABC):
    @abstractmethod
    def toString(self):
        pass
        
    # --- SORU 3: Alt sınıfların addFirst metoduna sahip olmasını zorunlu kılıyoruz ---
    @abstractmethod
    def addFirst(self, value):
        pass

class LinkedListEmpty(LinkedList):
    def __init__(self):
        pass
        
    # --- SORU 2: Boş liste için toString metodu ---
    def toString(self):
        return ""

    # --- SORU 3: Boş listeye eleman ekleme ---
    def addFirst(self, value):
        # Boş listenin başına eleman eklersek, yeni değer 'head', 
        # mevcut boş liste ('self') ise 'tail' olur.
        return LinkedListPopulated(value, self)

class LinkedListPopulated(LinkedList):
    def __init__(self, head, tail):
        self.head = head  
        self.tail = tail  
        
    # --- SORU 2: Dolu liste için toString metodu ---
    def toString(self):
        return str(self.head) + " " + self.tail.toString()

    # --- SORU 3: Dolu listeye eleman ekleme ---
    def addFirst(self, value):
        # En başa yepyeni bir kutu ekliyoruz. 
        # Yeni kutunun arkasına (tail) mevcut listenin tamamını (self) bağlıyoruz.
        return LinkedListPopulated(value, self)


# --- SORU 2 & 3: Test Kodları ---

print("--- Soru 2 Testleri ---")
list_0 = LinkedListEmpty()
print("List with 0 elements:", f"'{list_0.toString()}'")

list_1 = LinkedListPopulated(4, LinkedListEmpty())
print("List with 1 element:", f"'{list_1.toString()}'")

# Soru 3'teki örneği yapabilmek için [4, 7] listesini original_list olarak tanımlıyoruz
original_list = LinkedListPopulated(4, LinkedListPopulated(7, LinkedListEmpty()))
print("List with 2 elements (original_list):", f"'{original_list.toString()}'")

print("\n--- Soru 3 Testi ---")
# original_list'in başına 5 ekleyip new_list adında yeni bir liste elde ediyoruz
new_list = original_list.addFirst(5)
print("After addFirst(5):", f"'{new_list.toString()}'")