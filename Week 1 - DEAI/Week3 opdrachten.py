from abc import ABC, abstractmethod

# Temel soyut sınıfımız
class LinkedList(ABC):
    pass

# Boş listeyi temsil eden alt sınıf
class LinkedListEmpty(LinkedList):
    def __init__(self):
        # Boş listenin bir değeri veya devamı yoktur.
        pass

# Dolu listeyi temsil eden alt sınıf
class LinkedListPopulated(LinkedList):
    def __init__(self, head, tail):
        self.head = head  # O anki düğümün taşıdığı veri (değer)
        self.tail = tail  # Listenin geri kalanı (bir LinkedList nesnesi)