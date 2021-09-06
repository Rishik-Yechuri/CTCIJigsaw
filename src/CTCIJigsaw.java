import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CTCIJigsaw {
    public static void main(String[] args) {
        try
        {
            CTCIJigsaw obj = new CTCIJigsaw ();
            obj.run (args);
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    }
    public void run(String[] args){
        Jigsaw newPuzzle = new Jigsaw(2);
        PuzzlePiece[] shuffledPieces = newPuzzle.returnPieces();
        for(int x=0;x<shuffledPieces.length;x++){
            System.out.println("Piece " + (x+1) + ":\n" + "left:" + shuffledPieces[x].getSide("left") + "\nright:" + shuffledPieces[x].getSide("right") + "\ntop:" + shuffledPieces[x].getSide("top") + "\nbottom:" + shuffledPieces[x].getSide("bottom"));
        }
    }
}
class Jigsaw{
    PuzzlePiece[] holdPieces;
    public Jigsaw(int size){
        holdPieces = new PuzzlePiece[(int)Math.pow(size,2)];
        ArrayList<Integer> holdRandomNums = new ArrayList<Integer>();
        for(int x=0;x<Math.pow(size,2)*4;x++){
            holdRandomNums.add(x);
        }
        int currentRandomNum = holdRandomNums.remove(0);
        Collections.shuffle(holdRandomNums);
        for(int x=0;x<Math.pow(size,2);x++){
            PuzzlePiece newPiece = new PuzzlePiece();
            if(x % size != 0){
                newPiece.updateSide("left",holdPieces[x-1].getSide("right"));
            }
            if((x+1) % size != 0){
                currentRandomNum = holdRandomNums.remove(0);
                newPiece.updateSide("right",currentRandomNum);
            }
            if(x+size < Math.pow(size,2)){
                currentRandomNum = holdRandomNums.remove(0);
                newPiece.updateSide("bottom",currentRandomNum);
            }
            if(!(x < size)){
                newPiece.updateSide("top",holdPieces[x-size].getSide("bottom"));
            }
            holdPieces[x] = newPiece;
        }
        for(int x=0;x<Math.pow(size,2);x++){
            int numOfRotations = (int)(Math.random() * ((3) + 1));
            PuzzlePiece piece = holdPieces[x];
            for(int y=0;y<numOfRotations;y++){
                int savedID = piece.getSide("left");
                piece.updateSide("left",piece.getSide("bottom"));
                piece.updateSide("bottom",piece.getSide("right"));
                piece.updateSide("right",piece.getSide("top"));
                piece.updateSide("top",savedID);
            }
        }
        Collections.shuffle(Arrays.asList(holdPieces));
    }
    public PuzzlePiece[] returnPieces(){
        return holdPieces;
    }
}
class PuzzlePiece{
    public int topID = -1;
    public int rightID = -1;
    public int bottomID = -1;
    public int leftID = -1;
    public PuzzlePiece(){}
    public void updateSide(String whichSide,int newID){
        if(whichSide.equals("top")){
            topID = newID;
        }else if(whichSide.equals("right")){
            rightID = newID;
        }else if(whichSide.equals("bottom")){
            bottomID = newID;
        }else if(whichSide.equals("left")){
            leftID = newID;
        }
    }
    public int getSide(String whichSide){
        if(whichSide.equals("top")){
            return topID;
        }else if(whichSide.equals("right")){
            return rightID;
        }else if(whichSide.equals("bottom")){
            return bottomID;
        }else{
            return leftID;
        }
    }
}