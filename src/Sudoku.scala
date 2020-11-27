import scala.util.Random

class Sudoku(startConfig_ : Array[Array[Int]]) {
    var sudoku=startConfig_;
    var EMPTY:Int=0;

  def solver():List[Array[Array[Int]]]={
    var ListeSolutionsPossible:List[Array[Array[Int]]]=List();
    ListeSolutionsPossible = ListeSolutionsPossible:+solution()._2
    ListeSolutionsPossible
  }
    def fillXY(Sudoku_ :Array[Array[Int]],tuple2: Tuple2[Int,Int],number_ :Int){
      Sudoku_(tuple2._1)(tuple2._2)=number_;
    }
    def isPossibleAt(number_ : Int , x_ : Int,y_ : Int):Boolean={
         if(getAllX(x_,sudoku).contains(number_) || getAllY(y_,sudoku).contains(number_) || getAllQuadrant(Tuple2[Int,Int](x_,y_),sudoku)(0).contains(number_)  || getAllQuadrant(Tuple2[Int,Int](x_,y_),sudoku)(1).contains(number_)  || getAllQuadrant(Tuple2[Int,Int](x_,y_),sudoku)(2).contains(number_)){
            false
          }else{
            true
          }
    }
  def solution():Tuple2[Boolean,Array[Array[Int]]]={
    var workGrid=sudoku;
    workGrid.zipWithIndex.foreach((rows)=>{
      rows._1.zipWithIndex.foreach((columns)=>{
        if(columns._1 == EMPTY){
          for(a<-1 to 9){
            if(isPossibleAt(a,rows._2,columns._2)){
              //c'est bon
              fillXY(workGrid,Tuple2(rows._2,columns._2),a);
              if(solution()._1){
                Tuple2(true,workGrid);
              }else{
                fillXY(workGrid,Tuple2(rows._2,columns._2),EMPTY);
              }
            }
          }
          Tuple2(false,workGrid);
        }
      })
    })

    Tuple2(true,workGrid)
  }
    def getAllX(R_ : Int,sudoku_ : Array[Array[Int]]):Array[Int]={
      //Je récupere une Ligne= R_ (Row)
      sudoku_(R_)
    }
    def getAllY(C_ : Int,sudoku_ : Array[Array[Int]]):Array[Int]={
      //Je récupere Les éléments num=C_ de chaque Row(Ligne)
      var dataResult=Array[Int]();
      sudoku_.map((data)=>{
        data.zipWithIndex.map((dataWithIndex)=>{
          if(dataWithIndex._2==C_){
            dataResult = dataResult  :+ dataWithIndex._1
          }
        })
      });
      dataResult
    }
    def getAllQuadrant(XY_ : Tuple2[Int,Int],sudoku : Array[Array[Int]]): Array[Array[Int]]={
      var dataResult=Array[Array[Int]]();
      //la récupération des Lignes = Rows
      if(( XY_._1 >=0 &&  XY_._1 <3)){
        for( a <- 0 to 2){
            dataResult = dataResult:+getAllX(a,sudoku);
        }
      }else if(( XY_._1 >=3 &&  XY_._1 <6)){
        for( a <- 3 to 5){
          dataResult = dataResult:+getAllX(a,sudoku);
        }
      }else if(( XY_._1 >=6 &&  XY_._1 <9)){
        for( a <- 6 to 8){
          dataResult = dataResult:+getAllX(a,sudoku);
        }
      }
      //filtrage avec les numeros de Columns souhaité
      if(( XY_._2 >=0 &&  XY_._2 <3)){
         dataResult = dataResult.map((dataInside)=>{
           dataInside.slice(0,3)
         })
      }else if(( XY_._2 >=3 &&  XY_._2 <6)){
        dataResult = dataResult.map((dataInside)=>{
          dataInside.slice(3,6)
        })
      }else if(( XY_._2 >=6 &&  XY_._2 <9)){
        dataResult = dataResult.map((dataInside)=>{
          dataInside.slice(6,9)
        })
      }
    dataResult
    }

    def vide(): Array[Array[Int]]={
       sudoku
    }

    //creation de la grille
    def putOnTheX(X_ : Int , array: Array[Int]){
      sudoku(X_)=array;
    }

    def getNumberNotIn(rows_ : Array[Int],columns_ : Array[Int] , Quadrant : Array[Array[Int]]):Int={

      var number=getOneFromLeft(allToOneArray(Array(rows_ , columns_ ,Quadrant(0),Quadrant(1),Quadrant(2))));
        while(rows_.contains(number) || columns_.contains(number) || Quadrant(0).contains(number) || Quadrant(1).contains(number) || Quadrant(2).contains(number) ){
          number=getOneFromLeft(allToOneArray(Array(rows_ , columns_ ,Quadrant(0),Quadrant(1),Quadrant(2))));
        }
       number;
    }

    def allToOneArray(arrayList:Array[Array[Int]]):Array[Int]={
      var arrayToChoseFromIt=Array[Int]();
      for(a <- 1 to 9){
        //0 = rows ; 1=columns ; 2=Quadrant(0); 3=Quadrant(1) ; 4=Quadrant(0);
        if(  !arrayList(0).contains(a) && !arrayList(1).contains(a) && !arrayList(2).contains(a) && !arrayList(3).contains(a) && !arrayList(4).contains(a)  ){
          arrayToChoseFromIt = arrayToChoseFromIt:+a
        }
      }
      arrayToChoseFromIt
    }

    def getOneFromLeft(array: Array[Int]):Int={

        val rnd = new Random().nextInt(array.length)
        array(rnd)

    }

  override def toString =  {
    for (i <- 0 until sudoku.length) {
      if (i == 3 || i == 6) {
        System.out.println("------------------------------------")
      }
      for (j <- 0 until sudoku(i).length) {
        System.out.format("%-3s", sudoku(i)(j))
        if (j == 2 || j == 5 || j == 8) System.out.print(" | ")
      }
      System.out.println()
    }
    System.out.println("------------------------------------")
    ""
  }

  def proposeGrille(){
    var grille  =Array.fill(9,9)(0)
      for(r <- 0 to 8 ){
        for(c <- 0 to 3 ){
            grille(new Random().nextInt(8+1))(new Random().nextInt(8+1))=new Random().nextInt(9+1)
        }
      }

   sudoku=grille;
  }





}
