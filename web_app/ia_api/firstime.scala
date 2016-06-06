object Listes {
  var l1=List(1,3,5,4,6,2,7,9,8)
  var l2=List("a","b","c","e","g","d")
  var lkv=List(("a",1),("b",2),("c",3))

  def find_in_int(l:List[Int], e:Int): Boolean = {
    l match {
      case Nil => false
      case x::tails => x==e || find_in_int(tails,e)
    }
  }
  def split_list(l:List[Int]):(List[Int],List[Int]) = {
    l match {
      case Nil => (Nil,Nil)
      case x::Nil =>  (x,Nil)
      case a::b::tails => var (l1,l2)=split_list(tails);  (a::l1,b::l2)
    }
  }
}

Listes.find_in_int(Listes.l1,2)
Listes.find_in_int(Listes.l1,10)
Liste.split_list(Listes.l1)
Liste.split_list(Listes.l2)
