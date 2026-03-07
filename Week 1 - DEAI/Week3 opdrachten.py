from abc import ABC, abstractmethod

# --- SORU 1: Temel Sınıfların Oluşturulması ---

class LinkedList(ABC):
    @abstractmethod
    def toString(self):
        pass
        
    @abstractmethod
    def addFirst(self, value):
        pass

    @abstractmethod
    def remove(self, value):
        pass

    @abstractmethod
    def smallest(self):
        pass

    # --- SORU 6: Alt sınıfların sortSimple metoduna sahip olmasını zorunlu kılıyoruz ---
    @abstractmethod
    def sortSimple(self):
        pass

class LinkedListEmpty(LinkedList):
    def __init__(self):
        pass
        
    def toString(self):
        return ""

    def addFirst(self, value):
        return LinkedListPopulated(value, self)

    def remove(self, value):
        return self

    def smallest(self):
        return float('inf')

    # --- SORU 6: Boş listeyi sıralama ---
    def sortSimple(self):
        # Boş liste zaten sıralıdır, kendisini döndürürüz.
        return self

class LinkedListPopulated(LinkedList):
    def __init__(self, head, tail):
        self.head = head  
        self.tail = tail  
        
    def toString(self):
        return str(self.head) + " " + self.tail.toString()

    def addFirst(self, value):
        return LinkedListPopulated(value, self)

    def remove(self, value):
        if self.head == value:
            return self.tail
        else:
            return LinkedListPopulated(self.head, self.tail.remove(value))

    def smallest(self):
        rest_smallest = self.tail.smallest()
        if self.head < rest_smallest:
            return self.head
        else:
            return rest_smallest

    # --- SORU 6: Dolu listeyi sıralama ---
    def sortSimple(self):
        # 1. Listedeki en küçük değeri buluyoruz.
        min_value = self.smallest()
        
        # 2. En küçük değeri orijinal listeden (sadece bir kez) siliyoruz.
        rest_list = self.remove(min_value)
        
        # 3. Geri kalan listeyi kendi içinde sıralıyoruz (Özyineleme/Recursion).
        sorted_rest = rest_list.sortSimple()
        
        # 4. En küçük değeri, sıralanmış geri kalan listenin en başına ekliyoruz.
        return sorted_rest.addFirst(min_value)


# --- TEST KODLARI ---

print("--- Soru 2, 3, 4, 5 Testleri ---")
list_0 = LinkedListEmpty()
print("List with 0 elements:", f"'{list_0.toString()}'")

original_list = LinkedListPopulated(4, LinkedListPopulated(7, LinkedListEmpty()))
print("List with 2 elements:", f"'{original_list.toString()}'")

new_list = original_list.addFirst(5)
print("After addFirst(5):", f"'{new_list.toString()}'")

list_q4 = LinkedListPopulated(5, LinkedListPopulated(4, LinkedListPopulated(7, LinkedListPopulated(4, LinkedListEmpty()))))
print("Original list for Q4:", f"'{list_q4.toString()}'")

list_q4_removed_once = list_q4.remove(4)
print("After first remove(4):", f"'{list_q4_removed_once.toString()}'")

list_q5 = LinkedListPopulated(5, LinkedListPopulated(4, LinkedListPopulated(7, LinkedListEmpty())))
print("List for Q5:", f"'{list_q5.toString()}'")
print("Smallest value:", list_q5.smallest())


print("\n--- SORU 6 Testi ---")
# Ödevde istenen liste: 5, 4, 7, 4
list_q6 = LinkedListPopulated(5, LinkedListPopulated(4, LinkedListPopulated(7, LinkedListPopulated(4, LinkedListEmpty()))))
print("Original list for Q6:", f"'{list_q6.toString()}'")

# Listeyi sıralıyoruz
sorted_list = list_q6.sortSimple()
# Sıralanmış halini yazdırıyoruz (Beklenen: '4 4 5 7 ')
print("Sorted list:", f"'{sorted_list.toString()}'")