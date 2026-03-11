import sys
from abc import ABC, abstractmethod

# Çok büyük listelerde programın çökmemesi için Python'un iç içe girme (recursie) sınırını devasa bir sayıya çıkarıyoruz
sys.setrecursionlimit(20000000)

class LinkedList(ABC):
    @abstractmethod
    def toString(self):
        # Listeyi ekrana metin olarak yazdırmak için kullanılacak şablon metot
        pass

    @abstractmethod
    def addFirst(self, value):
        # Listenin en başına yeni bir eleman (vagon) eklemek için kullanılacak şablon metot
        pass

    @abstractmethod
    def subList(self, start, end):
        # Alt sınıfların subList metoduna sahip olmasını zorunlu kılıyoruz
        pass

    @abstractmethod
    def merge(self, other_list):
        # Alt sınıfların merge (birleştirme) metoduna sahip olmasını zorunlu kılıyoruz
        pass

    @abstractmethod
    def count(self):
        pass

    @abstractmethod
    def sortMerge(self):
        pass

class LinkedListEmpty(LinkedList):
    def toString(self):
        # Boş bir liste ekrana yazdırılırken hiçbir şey (boş metin) döndürür
        return ""

    def addFirst(self, value):
        # Boş bir listeye eleman eklediğimizde, o elemanı içeren yepyeni "dolu" bir liste oluşturup döndürürüz
        return LinkedListPopulated(value, self)
    
    def subList(self, start, end):
        # Boş bir listenin içinden parça kesemeyiz, doğrudan yine boş bir liste (kendimizi) döndürürüz.
        return self
    
    def merge(self, other_list):
        # Eğer ben boş bir listeysem, birleştirecek hiçbir şeyim yoktur.
        # Bu yüzden doğrudan karşıdan gelen diğer listeyi (other_list) döndürürüm.
        return other_list
    
    def count(self):
        # Boş listenin uzunluğu 0'dır
        return 0

    def sortMerge(self):
        # Boş bir listeyi sıralamaya gerek yoktur, kendisini döndürür
        return self

class LinkedListPopulated(LinkedList):
    def __init__(self, head, tail):
        # Dolu bir vagon oluştururken, vagonun içindeki değeri (head) ve arkasına takılacak listeyi (tail) belirleriz
        self.head = head
        self.tail = tail

    def toString(self):
        # Kendi değerimizi (head) metne çevirip, arkamızdaki vagonların metniyle (tail.toString) araya boşluk koyarak birleştiririz
        return str(self.head) + " " + self.tail.toString()

    def addFirst(self, value):
        # Zaten dolu olan bir listenin en başına yeni bir eleman eklerken,
        # yeni değeri başa koyup, mevcut listemizi (self) onun arkasına takarız
        return LinkedListPopulated(value, self)
    
    def subList(self, start, end):
        # Eğer bitiş noktasına ulaştıysak (end <= 0), daha fazla eleman almamıza gerek yok.
        # Kesme işlemini bitirip boş vagon takıyoruz.
        if end <= 0:
            return LinkedListEmpty()
            
        # Eğer başlangıç noktasına henüz ulaşmadıysak (start > 0), bu vagonu atla ve sonrakine geç.
        # Hedefimize yaklaşmak için hem start hem de end değerini 1 azaltıyoruz.
        if start > 0:
            return self.tail.subList(start - 1, end - 1)
            
        # Eğer başlangıç noktasına ulaştıysak (start == 0) ve bitişe henüz gelmediysek,
        # bu vagonun değerini (self.head) al listeye dahil et ve sonrakileri almak için tail'e sor.
        return LinkedListPopulated(self.head, self.tail.subList(0, end - 1))
    
    def merge(self, other_list):
        # Eğer karşıdan gelen liste boşsa, birleştirecek bir şey yoktur, kendimi döndürürüm.
        if isinstance(other_list, LinkedListEmpty):
            return self
            
        # Eğer benim başımdaki değer, diğer listenin başındaki değerden KÜÇÜK veya EŞİTSE:
        if self.head <= other_list.head:
            # Ben başa geçerim! Arkamdaki vagonlara (tail) da "Diğer listeyle birleşmeye devam edin" derim.
            return LinkedListPopulated(self.head, self.tail.merge(other_list))
        else:
            # Eğer diğer listenin başındaki değer benden KÜÇÜKSE:
            # O başa geçer! Ben ise onun arkasındaki vagonlarla birleşmek üzere beklerim.
            return LinkedListPopulated(other_list.head, self.merge(other_list.tail))
    def count(self):
        # Kendim için 1 sayıyorum, arkamdakilerin sayısıyla topluyorum
        return 1 + self.tail.count()

    def sortMerge(self):
        # 1. Adım: Listenin uzunluğunu bul
        length = self.count()
        
        # Eğer listede sadece 1 eleman varsa, zaten sıralıdır! Bölmeye gerek yok, kendini döndür.
        if length == 1:
            return self
            
        # 2. Adım: Listeyi tam ortasından ikiye bölmek için orta noktayı (mid) bul
        mid = length // 2
        
        # 3. Adım: subList kullanarak listeyi sol (left) ve sağ (right) olarak iki parçaya ayır
        left_half = self.subList(0, mid)
        right_half = self.subList(mid, length)
        
        # 4. Adım: BÖL VE FETHET!
        # Sol tarafı kendi içinde sırala, sağ tarafı kendi içinde sırala...
        # Sonra bu iki sıralanmış parçayı 'merge' (fermuar) ile kusursuzca birleştir!
        return left_half.sortMerge().merge(right_half.sortMerge())

# --- TEST KODLARI BURADAN AŞAĞIYA YAZILACAK ---

# --- 1. Soru Testi ---
print("\n--- Soru 1: subList Testi ---")

# Hocanın örneğindeki '5', '4', '7', '4' listesini oluşturalım
# (addFirst en başa eklediği için tersten ekliyoruz ki sıralama doğru olsun)
my_list = LinkedListEmpty().addFirst('4').addFirst('7').addFirst('4').addFirst('5')
print("Orijinal Liste:", my_list.toString())

# 1. indeksten başlayıp 3. indekse kadar (3 hariç) keselim
sub_list = my_list.subList(1, 3)

# Beklenen sonuç: 4 7
print("subList(1, 3) Sonucu:", sub_list.toString())

# --- 3. Soru Testi ---
print("\n--- Soru 3: merge Testi ---")

# 1. Listeyi oluşturalım: 4, 4, 5, 7 (Tersten ekliyoruz)
list1 = LinkedListEmpty().addFirst('7').addFirst('5').addFirst('4').addFirst('4')
print("1. Liste:", list1.toString())

# 2. Listeyi oluşturalım: 2, 6, 7 (Tersten ekliyoruz)
list2 = LinkedListEmpty().addFirst('7').addFirst('6').addFirst('2')
print("2. Liste:", list2.toString())

# İki listeyi birleştiriyoruz (merge)
merged_list = list1.merge(list2)

# Beklenen sonuç: 2 4 4 5 6 7 7
print("Birleştirilmiş (Merged) Liste:", merged_list.toString())

# --- 6. Soru Testi ---
print("\n--- Soru 6: sortMerge Testi ---")

# Hocanın karmaşık listesini oluşturalım: 5, 4, 7, 4
unsorted_list = LinkedListEmpty().addFirst('4').addFirst('7').addFirst('4').addFirst('5')
print("Sırasız Liste:", unsorted_list.toString())

# Merge Sort algoritmasını çalıştıralım!
sorted_merge_list = unsorted_list.sortMerge()

# Beklenen sonuç: 4 4 5 7
print("Merge Sort ile Sıralanmış Liste:", sorted_merge_list.toString())
