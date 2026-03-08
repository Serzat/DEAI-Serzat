import csv
import sys
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

    @abstractmethod
    def uniq(self):
        pass

    @abstractmethod
    def count(self):
        pass

    # --- SORU 9: Alt sınıfların reverse metoduna sahip olmasını zorunlu kılıyoruz ---
    @abstractmethod
    def reverse(self, acc=None):
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
        return None

    # --- SORU 6: Boş listeyi sıralama ---
    def sortSimple(self):
        # Boş liste zaten sıralıdır, kendisini döndürürüz.
        return self
    
   
    # --- SORU 8: Boş listede kopya silme ve sayma ---
    def uniq(self):
        # Boş bir listede tekrar eden eleman olamaz, kendini döndürür
        return self

    def count(self):
        # Boş listenin eleman sayısı sıfırdır
        return 0
    
    # --- SORU 9: Boş listeyi tersine çevirme ---
    def reverse(self, acc=None):
        # Eğer biriktirici (acc) henüz oluşturulmamışsa, boş listenin tersi yine kendisidir.
        if acc is None:
            return self
        # Eğer biriktirici liste doluysa ve zincirin sonuna (boş kutuya) ulaştıysak,
        # tersine çevrilmiş ve birikmiş o yeni listeyi geri döndürüyoruz.
        return acc

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

    # --- SORU 5: Dolu listede en küçük elemanı bulma ---
    def smallest(self):
        rest_smallest = self.tail.smallest()
        
        # Eğer arkamızdaki liste boşsa (None döndüyse), kıyaslayacak kimse yoktur. 
        # Bu yüzden en küçük değer mecburen bizim kendi değerimizdir.
        if rest_smallest is None:
            return self.head
            
        # Eğer arkada başka vagonlar varsa, onlardan gelen değerle kendimizi kıyaslarız.
        # Bu yapı hem sayılarla (4 < 7) hem de metinlerle ("A" < "Z") sorunsuz çalışır.
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
    
    # --- SORU 8: Dolu listede kopya silme ve sayma ---
    def uniq(self):
        # Önce arkamızdaki vagonların kendi içindeki kopyaları temizlemesini istiyoruz
        unique_tail = self.tail.uniq()
        
        # Eğer arkamızdaki liste boş DEĞİLSE ve bizim plakamız arkamızdaki ilk plakayla AYNIYSA:
        if isinstance(unique_tail, LinkedListPopulated) and self.head == unique_tail.head:
            # Biz fazlalığız (kopyayız), kendimizi atlıyoruz ve doğrudan arkamızı döndürüyoruz
            return unique_tail
        else:
            # Eğer plakamız benzersizse kendimizi koruyoruz ve temizlenmiş kuyruğu arkamıza takıyoruz
            return LinkedListPopulated(self.head, unique_tail)

    def count(self):
        # Kendi varlığımız için 1 sayıyoruz, arkamızdaki vagonların sayısıyla topluyoruz
        return 1 + self.tail.count()
    
    # --- SORU 9: Dolu listeyi tersine çevirme ---
    def reverse(self, acc=None):
        # Metot ilk kez çağrıldığında acc (biriktirici) boştur. 
        # Hemen kendimize yeni, boş bir liste oluşturuyoruz.
        if acc is None:
            acc = LinkedListEmpty()
            
        # Kendi değerimizi (self.head) alıp, bu yeni biriktirici listenin EN BAŞINA ekliyoruz.
        # Bu sayede ilk aldığımız eleman, işlem bittiğinde en sonda kalmış olacak.
        new_acc = acc.addFirst(self.head)
        
        # Orijinal listedeki bir sonraki vagona (tail) geçiyoruz ve
        # "Elimdeki bu yeni listeye (new_acc) sen de kendi değerini ekle" diyerek işi devrediyoruz.
        return self.tail.reverse(new_acc)


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

# --- SORU 7: Plaka Dosyasını Okuma ve Sıralama ---
print("\n--- SORU 7 Testi ---")

# Derin özyineleme (recursion) işlemleri için Python'un sınırını artırıyoruz
sys.setrecursionlimit(20000000)

# Boş bir plaka listesi oluşturuyoruz
license_plates = LinkedListEmpty()

# kentekens1000.txt dosyasını açıp okuyoruz
with open('Week 1 - DEAI/kentekens1000.txt', 'r') as f:
    reader = csv.reader(f, delimiter=',')
    for row in reader:
        # Her satırın ilk elemanını (plakayı) listemizin başına ekliyoruz
        license_plates = license_plates.addFirst(row[0])

print("File loaded. Sorting started... (This may take a few seconds depending on your computer)")

# Listeyi küçükten büyüğe sıralıyoruz
sorted_plates = license_plates.sortSimple()

# Sıralanmış listeyi ekrana yazdırıyoruz
print("Sorted Plates:\n", sorted_plates.toString())

# --- SORU 8 Testi ---
print("\n--- SORU 8 Testi ---")

# Önce dosyadaki toplam plaka sayısını sayalım
total_plates = sorted_plates.count()
print("Total number of plates:", total_plates)

# Listemizi kopyalardan arındıralım (uniq)
unique_plates = sorted_plates.uniq()

# Kopyalar silindikten sonra kalan benzersiz plakaları sayalım
unique_count = unique_plates.count()
print("Number of UNIQUE plates:", unique_count)

# --- SORU 9 Testi ---
print("\n--- SORU 9 Testi ---")

# Kopyalardan arındırılmış (unique_plates) listemizi tersine çeviriyoruz
reversed_plates = unique_plates.reverse()

# Ters çevrilmiş listeyi ekrana yazdırıyoruz
# Sonuç Z harfiyle başlayıp A'ya ve sayılara doğru geriye gitmeli!
print("Reversed Plates (Z to A):\n", reversed_plates.toString())