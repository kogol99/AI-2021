package MinMax;

import Gra.Kalaha;

import java.util.Random;

public class MinMax {
    private int maxDepth = 11;
    private int player = -1;
    private int nextMoveNumber = -1;

    public int findBest(Kalaha initialPosition, int player) {
        if (initialPosition.czyPierwszyRuch()){
            return 1 + new Random().nextInt(5);
        }

        this.player = player;
        this.nextMoveNumber = -1;
        minimax(initialPosition, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return nextMoveNumber;
    }

    private int minimax(Kalaha position, int depth, int alpha, int beta, boolean maximizingPlayer) {
//        System.out.println(depth);

        if (depth == 0 || position.sprawdzCzyKoniec()) {
            return position.getPrzewagaGracza(player);
        }


        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Kalaha child : position.getDzieciGracza(player)) {
                int eval = minimax(child, depth - 1, alpha, beta, false);
                if (depth == maxDepth && eval > maxEval)
                    if (child.getAktualnyGracz() == 1){
                        nextMoveNumber = child.getWybranyIndex() +1;
                    } else {
                        nextMoveNumber = child.getWybranyIndex() - child.getMapa().getRozmiarMapy()/2 + 1;
                    }
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                /*if (beta <= alpha)
                    break;*/
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Kalaha child : position.getDzieciGracza(player)) {
                int eval = minimax(child, depth - 1, alpha, beta, true);
                if (depth == maxDepth && eval > minEval)
                    if (child.getAktualnyGracz() == 1){
                        nextMoveNumber = child.getWybranyIndex() +1;
                    } else {
                        nextMoveNumber = child.getWybranyIndex() - child.getMapa().getRozmiarMapy()/2 + 1;
                    }                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                /*if (beta <= alpha)
                    break;*/
            }
            return minEval;
        }
    }
}
