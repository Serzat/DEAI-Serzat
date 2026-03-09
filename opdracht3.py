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
