package ca.ggolda.android_tab_chess;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by gcgol on 12/29/2016.
 */

public class Movement {

    private static ImageView a1 = GameActivity.a1;
    private static ImageView a2 = GameActivity.a2;
    private static ImageView a3 = GameActivity.a3;
    private static ImageView a4 = GameActivity.a4;
    private static ImageView a5 = GameActivity.a5;
    private static ImageView a6 = GameActivity.a6;
    private static ImageView a7 = GameActivity.a7;
    private static ImageView a8 = GameActivity.a8;

    private static ImageView b1 = GameActivity.b1;
    private static ImageView b2 = GameActivity.b2;
    private static ImageView b3 = GameActivity.b3;
    private static ImageView b4 = GameActivity.b4;
    private static ImageView b5 = GameActivity.b5;
    private static ImageView b6 = GameActivity.b6;
    private static ImageView b7 = GameActivity.b7;
    private static ImageView b8 = GameActivity.b8;

    private static ImageView c1 = GameActivity.c1;
    private static ImageView c2 = GameActivity.c2;
    private static ImageView c3 = GameActivity.c3;
    private static ImageView c4 = GameActivity.c4;
    private static ImageView c5 = GameActivity.c5;
    private static ImageView c6 = GameActivity.c6;
    private static ImageView c7 = GameActivity.c7;
    private static ImageView c8 = GameActivity.c8;

    private static ImageView d1 = GameActivity.d1;
    private static ImageView d2 = GameActivity.d2;
    private static ImageView d3 = GameActivity.d3;
    private static ImageView d4 = GameActivity.d4;
    private static ImageView d5 = GameActivity.d5;
    private static ImageView d6 = GameActivity.d6;
    private static ImageView d7 = GameActivity.d7;
    private static ImageView d8 = GameActivity.d8;

    private static ImageView e1 = GameActivity.e1;
    private static ImageView e2 = GameActivity.e2;
    private static ImageView e3 = GameActivity.e3;
    private static ImageView e4 = GameActivity.e4;
    private static ImageView e5 = GameActivity.e5;
    private static ImageView e6 = GameActivity.e6;
    private static ImageView e7 = GameActivity.e7;
    private static ImageView e8 = GameActivity.e8;

    private static ImageView f1 = GameActivity.f1;
    private static ImageView f2 = GameActivity.f2;
    private static ImageView f3 = GameActivity.f3;
    private static ImageView f4 = GameActivity.f4;
    private static ImageView f5 = GameActivity.f5;
    private static ImageView f6 = GameActivity.f6;
    private static ImageView f7 = GameActivity.f7;
    private static ImageView f8 = GameActivity.f8;

    private static ImageView g1 = GameActivity.g1;
    private static ImageView g2 = GameActivity.g2;
    private static ImageView g3 = GameActivity.g3;
    private static ImageView g4 = GameActivity.g4;
    private static ImageView g5 = GameActivity.g5;
    private static ImageView g6 = GameActivity.g6;
    private static ImageView g7 = GameActivity.g7;
    private static ImageView g8 = GameActivity.g8;

    private static ImageView h1 = GameActivity.h1;
    private static ImageView h2 = GameActivity.h2;
    private static ImageView h3 = GameActivity.h3;
    private static ImageView h4 = GameActivity.h4;
    private static ImageView h5 = GameActivity.h5;
    private static ImageView h6 = GameActivity.h6;
    private static ImageView h7 = GameActivity.h7;
    private static ImageView h8 = GameActivity.h8;

    private static int selectedSquare = GameActivity.selectedSquare;

    private static String selectedUnit = GameActivity.selectedUnit;

    private static List<String> gamesetList = GameActivity.gamesetList;

    private static String gamesetString = GameActivity.gamesetString;

    public static String playerColor = GameActivity.playerColor;

    public static String turn  = GameActivity.turn;

    public static TextView currentTurn = GameActivity.currentTurn;

    public static DatabaseReference mGamesDatabaseReference = GameActivity.mGamesDatabaseReference;

    public static String match_id = GameActivity.match_id;


    // make possible views active
    // passing in image view to be able to set color back on second click
    public static void possibleMoves() {




//            __      ___    _ _       ___
//            \ \    / / |_ (_) |_ ___| _ \__ ___ __ ___ _
//             \ \/\/ /| ' \| |  _/ -_)  _/ _` \ V  V / ' \
//              \_/\_/ |_||_|_|\__\___|_| \__,_|\_/\_/|_||_|

        if (selectedUnit.equals("white_pawn")) {
            Log.e("EYHOSEL", selectedUnit);

            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));


                // if statement for +1 forward
                if ((i == selectedSquare + 8) && isFree(i)) {

                    if (space != null) {
                        space.setBackgroundColor(Color.parseColor("#A600FF00"));
                        space.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.moveGamepiece(localI);
                            }
                        });
                    }
                }

                // if statement for double move on first go
                if ((i == selectedSquare + 16) && isFree(i) && gamesetList.get(i).equals("free_square") && ((i == 24) || (i == 25) || (i == 26) || (i == 27) || (i == 28) || (i == 29) || (i == 30) || (i == 31))) {

                    if (space != null) {
                        space.setBackgroundColor(Color.parseColor("#A600FF00"));
                        space.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.moveGamepiece(localI);
                            }
                        });
                    }
                }


                // if statement for pawn kill
                if (((i == selectedSquare + 7) || (i == selectedSquare + 9))) {
                    if ((gamesetList.get(i).equals("black_pawn") || gamesetList.get(i).equals("black_king") || gamesetList.get(i).equals("black_queen") || gamesetList.get(i).equals("black_rook") || gamesetList.get(i).equals("black_knight") || gamesetList.get(i).equals("black_bishop"))) {

                        Log.e("EHYOe", "um" + gamesetList.get(i));

                        if (space != null) {
                            space.setBackgroundColor(Color.parseColor("#A600FF00"));
                            space.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    GameActivity.moveGamepiece(localI);
                                }
                            });
                        }
                        wallPawn(i);
                    }
                }


            }


        }

//             ___ _         _   ___
//            | _ ) |__ _ __| |_| _ \__ ___ __ ___ _
//            | _ \ / _` / _| / /  _/ _` \ V  V / ' \
//            |___/_\__,_\__|_\_\_| \__,_|\_/\_/|_||_|

        if (selectedUnit.equals("black_pawn")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                // if statement for -1 forward
                if ((i == selectedSquare - 8) && isFree(i)) {

                    if (space != null) {
                        space.setBackgroundColor(Color.parseColor("#A600FF00"));
                        space.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.moveGamepiece(localI);
                            }
                        });
                    }
                }

                // if statement for double move on first go
                if ((i == selectedSquare - 16) && isFree(i) && gamesetList.get(i + 8).equals("free_square") && ((i == 32) || (i == 33) || (i == 34) || (i == 35) || (i == 36) || (i == 37) || (i == 38) || (i == 39))) {

                    if (space != null) {
                        space.setBackgroundColor(Color.parseColor("#A600FF00"));
                        space.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.moveGamepiece(localI);
                            }
                        });
                    }
                }

                // if statement for pawn kill
                if (((i == selectedSquare - 7) || (i == selectedSquare - 9))) {
                    if ((gamesetList.get(i).equals("white_pawn") || gamesetList.get(i).equals("white_king") || gamesetList.get(i).equals("white_queen") || gamesetList.get(i).equals("white_rook") || gamesetList.get(i).equals("white_knight") || gamesetList.get(i).equals("white_bishop"))) {

                        Log.e("EHYOe", "um" + gamesetList.get(i));

                        if (space != null) {
                            space.setBackgroundColor(Color.parseColor("#A600FF00"));
                            space.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    GameActivity.moveGamepiece(localI);
                                }
                            });
                        }
                        wallPawn(i);
                    }
                }


            }
        }


//            __      ___    _ _       _  __     _      _   _
//            \ \    / / |_ (_) |_ ___| |/ /_ _ (_)__ _| |_| |_
//             \ \/\/ /| ' \| |  _/ -_) ' <| ' \| / _` | ' \  _|
//              \_/\_/ |_||_|_|\__\___|_|\_\_||_|_\__, |_||_\__|
//                                                |___/
        if (selectedUnit.equals("white_knight")) {
            Log.e("EYHOSEL", selectedUnit);

            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                // if statement for +1 forward
                if ((i == selectedSquare + 6) || (i == selectedSquare + 10) || (i == selectedSquare + 15) || (i == selectedSquare + 17) || (i == selectedSquare - 6) || (i == selectedSquare - 10) || (i == selectedSquare - 15) || (i == selectedSquare - 17)) {

                    if (isFree(i) || (gamesetList.get(i).equals("black_pawn")) || (gamesetList.get(i).equals("black_king")) || (gamesetList.get(i).equals("black_queen")) || (gamesetList.get(i).equals("black_bishop")) || (gamesetList.get(i).equals("black_knight")) || (gamesetList.get(i).equals("black_rook"))) {
                        if (space != null) {
                            space.setBackgroundColor(Color.parseColor("#A600FF00"));
                            space.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    GameActivity.moveGamepiece(localI);
                                }
                            });

                            wallKnight();

                        }
                    }
                }
            }
        }


//            ___ _         _   _  __     _      _   _
//           | _ ) |__ _ __| |_| |/ /_ _ (_)__ _| |_| |_
//           | _ \ / _` / _| / / ' <| ' \| / _` | ' \  _|
//           |___/_\__,_\__|_\_\_|\_\_||_|_\__, |_||_\__|
//                                          |___/

        if (selectedUnit.equals("black_knight")) {
            Log.e("EYHOSEL", selectedUnit);

            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                // if statement for Knight movement
                if ((i == selectedSquare + 6) || (i == selectedSquare + 10) || (i == selectedSquare + 15) || (i == selectedSquare + 17) || (i == selectedSquare - 6) || (i == selectedSquare - 10) || (i == selectedSquare - 15) || (i == selectedSquare - 17)) {

                    if (isFree(i) || (gamesetList.get(i).equals("white_pawn")) || (gamesetList.get(i).equals("white_king")) || (gamesetList.get(i).equals("white_queen")) || (gamesetList.get(i).equals("white_bishop")) || (gamesetList.get(i).equals("white_knight")) || (gamesetList.get(i).equals("white_rook"))) {
                        if (space != null) {
                            space.setBackgroundColor(Color.parseColor("#A600FF00"));
                            space.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    GameActivity.moveGamepiece(localI);
                                }
                            });

                            wallKnight();
                        }
                    }
                }
            }
        }


//            __      ___    _ _       ___          _
//            \ \    / / |_ (_) |_ ___| _ \___  ___| |__
//             \ \/\/ /| ' \| |  _/ -_)   / _ \/ _ \ / /
//              \_/\_/ |_||_|_|\__\___|_|_\___/\___/_\_\

        if (selectedUnit.equals("white_rook")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 16:

                        if (heldBlackOrFree(i) && isFree(i - 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 24:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 32:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 40:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 48:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 56:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40) && isFree(i - 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;

                    // DOWN
                    case -8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -16:

                        if (heldBlackOrFree(i) && isFree(i + 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -24:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -32:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -40:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -48:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -56:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40) && isFree(i + 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;


                    // RIGHT
                    case 1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallRook(i);


                        break;
                    case 2:

                        if (heldBlackOrFree(i) && isFree(i - 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;
                    case 3:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 4:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 5:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 6:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;
                    case 7:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5) && isFree(i - 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;

                    // LEFT
                    case -1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case -2:

                        if (heldBlackOrFree(i) && isFree(i + 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;
                    case -3:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -4:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -5:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -6:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;
                    case -7:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5) && isFree(i + 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;


                }
            }
        }
//             ___ _         _   ___          _
//            | _ ) |__ _ __| |_| _ \___  ___| |__
//            | _ \ / _` / _| / /   / _ \/ _ \ / /
//            |___/_\__,_\__|_\_\_|_\___/\___/_\_\

        if (selectedUnit.equals("black_rook")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 16:

                        if (heldWhiteOrFree(i) && isFree(i - 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 24:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 32:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 40:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 48:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 56:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40) && isFree(i - 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;

                    // DOWN
                    case -8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -16:

                        if (heldWhiteOrFree(i) && isFree(i + 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -24:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -32:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -40:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -48:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -56:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40) && isFree(i + 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;


                    // RIGHT
                    case 1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallRook(i);


                        break;
                    case 2:

                        if (heldWhiteOrFree(i) && isFree(i - 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;
                    case 3:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 4:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 5:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case 6:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;
                    case 7:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5) && isFree(i - 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);

                        break;

                    // LEFT
                    case -1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallRook(i);


                        break;
                    case -2:

                        if (heldWhiteOrFree(i) && isFree(i + 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;
                    case -3:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -4:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -5:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);


                        break;
                    case -6:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;
                    case -7:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5) && isFree(i + 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallRook(i);

                        break;


                }

            }
        }

//            __      ___    _ _       ___ _    _
//            \ \    / / |_ (_) |_ ___| _ |_)__| |_  ___ _ __
//             \ \/\/ /| ' \| |  _/ -_) _ \ (_-< ' \/ _ \ '_ \
//              \_/\_/ |_||_|_|\__\___|___/_/__/_||_\___/ .__/
//                                                      |_|
        if (selectedUnit.equals("white_bishop")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP-RIGHT
                    case 54:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36) && isFree(selectedSquare + 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 45:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 36:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 27:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 18:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case 9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    // DOWN-RIGHT
                    case -42:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28) && isFree(selectedSquare - 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -35:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -28:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -21:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -14:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case -7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    // UP-LEFT
                    case 42:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28) && isFree(selectedSquare + 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 35:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 28:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 21:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 14:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case 7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;


                    // DOWN LEFT
                    case -54:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36) && isFree(selectedSquare - 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -45:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -36:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -27:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -18:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case -9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;


                }
            }
        }


//             ___ _         _   ___ _    _
//            | _ ) |__ _ __| |_| _ |_)__| |_  ___ _ __
//            | _ \ / _` / _| / / _ \ (_-< ' \/ _ \ '_ \
//            |___/_\__,_\__|_\_\___/_/__/_||_\___/ .__/
//                                                |_|
        if (selectedUnit.equals("black_bishop")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP-RIGHT
                    case 54:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36) && isFree(selectedSquare + 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 45:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 36:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 27:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 18:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case 9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    // DOWN-RIGHT
                    case -42:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28) && isFree(selectedSquare - 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -35:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -28:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -21:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -14:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case -7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    // UP-LEFT
                    case 42:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28) && isFree(selectedSquare + 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 35:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 28:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 21:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case 14:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case 7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;


                    // DOWN LEFT
                    case -54:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36) && isFree(selectedSquare - 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -45:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -36:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -27:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallBishop(i);
                        break;

                    case -18:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;

                    case -9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallBishop(i);
                        break;


                }
            }
        }


//            __      ___    _ _        ___
//            \ \    / / |_ (_) |_ ___ / _ \ _  _ ___ ___ _ _
//             \ \/\/ /| ' \| |  _/ -_) (_) | || / -_) -_) ' \
//              \_/\_/ |_||_|_|\__\___|\__\_\\_,_\___\___|_||_|

        if (selectedUnit.equals("white_queen")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                // FOR DIAGONAL
                switch (iDiff) {
                    // UP-RIGHT
                    case 54:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36) && isFree(selectedSquare + 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 45:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 36:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 27:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 18:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case 9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    // DOWN-RIGHT
                    case -42:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28) && isFree(selectedSquare - 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -35:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -28:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -21:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -14:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case -7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        GameActivity.moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    // UP-LEFT
                    case 42:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28) && isFree(selectedSquare + 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 35:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 28:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 21:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 14:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare + 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case 7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;


                    // DOWN LEFT
                    case -54:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36) && isFree(selectedSquare - 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -45:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -36:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -27:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -18:
                        if (heldBlackOrFree(i) && (isFree(selectedSquare - 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case -9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;
                }


                //FOR VERTICAL
                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 16:

                        if (heldBlackOrFree(i) && isFree(i - 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 24:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 32:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 40:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 48:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 56:

                        if (heldBlackOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40) && isFree(i - 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;

                    // DOWN
                    case -8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -16:

                        if (heldBlackOrFree(i) && isFree(i + 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -24:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -32:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -40:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -48:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -56:

                        if (heldBlackOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40) && isFree(i + 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;


                    // RIGHT
                    case 1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);


                        break;
                    case 2:

                        if (heldBlackOrFree(i) && isFree(i - 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;
                    case 3:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 4:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 5:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 6:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;
                    case 7:

                        if (heldBlackOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5) && isFree(i - 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;

                    // LEFT
                    case -1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case -2:

                        if (heldBlackOrFree(i) && isFree(i + 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;
                    case -3:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -4:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -5:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -6:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;
                    case -7:

                        if (heldBlackOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5) && isFree(i + 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;


                }

            }
        }

//             ___ _         _    ___
//            | _ ) |__ _ __| |__/ _ \ _  _ ___ ___ _ _
//            | _ \ / _` / _| / / (_) | || / -_) -_) ' \
//            |___/_\__,_\__|_\_\\__\_\\_,_\___\___|_||_|

        if (selectedUnit.equals("black_queen")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                // FOR DIAGONAL
                switch (iDiff) {
                    // UP-RIGHT
                    case 54:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36) && isFree(selectedSquare + 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 45:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27) && isFree(selectedSquare + 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 36:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18) && isFree(selectedSquare + 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 27:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9) && isFree(selectedSquare + 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 18:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case 9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    // DOWN-RIGHT
                    case -42:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28) && isFree(selectedSquare - 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -35:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21) && isFree(selectedSquare - 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -28:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14) && isFree(selectedSquare - 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -21:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7) && isFree(selectedSquare - 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -14:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case -7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    // UP-LEFT
                    case 42:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28) && isFree(selectedSquare + 35))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 35:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21) && isFree(selectedSquare + 28))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 28:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14) && isFree(selectedSquare + 21))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 21:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7) && isFree(selectedSquare + 14))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case 14:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare + 7))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case 7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;


                    // DOWN LEFT
                    case -54:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36) && isFree(selectedSquare - 45))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -45:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27) && isFree(selectedSquare - 36))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -36:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18) && isFree(selectedSquare - 27))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -27:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9) && isFree(selectedSquare - 18))) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);
                        break;

                    case -18:
                        if (heldWhiteOrFree(i) && (isFree(selectedSquare - 9))) {

                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;

                    case -9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);
                        break;
                }


                //FOR VERTICAL
                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 16:

                        if (heldWhiteOrFree(i) && isFree(i - 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 24:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 32:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 40:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case 48:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case 56:

                        if (heldWhiteOrFree(i) && isFree(i - 8) && isFree(i - 16) && isFree(i - 24) && isFree(i - 32) && isFree(i - 40) && isFree(i - 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;

                    // DOWN
                    case -8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -16:

                        if (heldWhiteOrFree(i) && isFree(i + 8)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -24:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -32:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -40:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                    case -48:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        break;
                    case -56:

                        if (heldWhiteOrFree(i) && isFree(i + 8) && isFree(i + 16) && isFree(i + 24) && isFree(i + 32) && isFree(i + 40) && isFree(i + 48)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        break;


                    // RIGHT
                    case 1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }

                        }
                        wallQueen(i);


                        break;
                    case 2:

                        if (heldWhiteOrFree(i) && isFree(i - 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;
                    case 3:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 4:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 5:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case 6:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;
                    case 7:

                        if (heldWhiteOrFree(i) && isFree(i - 1) && isFree(i - 2) && isFree(i - 3) && isFree(i - 4) && isFree(i - 5) && isFree(i - 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);

                        break;

                    // LEFT
                    case -1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }
                        wallQueen(i);


                        break;
                    case -2:

                        if (heldWhiteOrFree(i) && isFree(i + 1)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;
                    case -3:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -4:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -5:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);


                        break;
                    case -6:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;
                    case -7:

                        if (heldWhiteOrFree(i) && isFree(i + 1) && isFree(i + 2) && isFree(i + 3) && isFree(i + 4) && isFree(i + 5) && isFree(i + 6)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }

                        wallQueen(i);

                        break;


                }

            }
        }


//            __      ___    _ _       _  ___
//            \ \    / / |_ (_) |_ ___| |/ (_)_ _  __ _
//             \ \/\/ /| ' \| |  _/ -_) ' <| | ' \/ _` |
//              \_/\_/ |_||_|_|\__\___|_|\_\_|_||_\__, |
//                                                |___/
        if (selectedUnit.equals("white_king")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // DOWN
                    case -8:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // RIGHT
                    case 1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // TOP-RIGHT
                    case 9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // BOTTOM-RIGHT
                    case -9:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // LEFT
                    case -1:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // TOP-LEFT
                    case 7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // BOTTOM-LEFT
                    case -7:
                        if (heldBlackOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                }
            }
        }

//             ___ _         _   _  ___
//            | _ ) |__ _ __| |_| |/ (_)_ _  __ _
//            | _ \ / _` / _| / / ' <| | ' \/ _` |
//            |___/_\__,_\__|_\_\_|\_\_|_||_\__, |
//                                          |___/
        if (selectedUnit.equals("black_king")) {
            Log.e("EYHOSEL", selectedUnit);


            for (int i = 0; i < 64; i++) {
                final int localI = i;
                final ImageView space = getSquareImageView(i);

                Log.e("EYHOSEL", gamesetList.get(i));

                // subtract int selectedSquare to get the difference of i
                int iDiff = i - selectedSquare;

                switch (iDiff) {
                    // UP
                    case 8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // DOWN
                    case -8:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // RIGHT
                    case 1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // TOP-RIGHT
                    case 9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // BOTTOM-RIGHT
                    case -9:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // LEFT
                    case -1:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // TOP-LEFT
                    case 7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;

                    // BOTTOM-LEFT
                    case -7:
                        if (heldWhiteOrFree(i)) {
                            if (space != null) {
                                space.setBackgroundColor(Color.parseColor("#A600FF00"));
                                space.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        moveGamepiece(localI);
                                    }
                                });
                            }
                        }


                        break;
                }
            }


        }
    }


    //Queen Fix
    //TODO: create separate queen function instead of combining rook + bishop
    private static void wallQueen(int i) {
        //if ((i == 9) || (i == 18) || (i == 27) || (i == 36) || (i == 45) || (i == 54) || (i == 63) || (i == 7) ||(i == 14) || (i == 21) || (i == 28) || (i == 35) || (i == 42) || (i == 49) || (i == 56) || (i == -9) || (i == -18) || (i == -27) || (i == -36) || (i == -45) || (i == -54) || (i == -63) || (i == -7) ||(i == -14) || (i == -21) || (i == -28) || (i == -35) || (i == -42) || (i == -49) || (i == -56)) {
        wallBishop(i);
        // } else {
        wallRook(i);

        //  }

    }


    //TODO: figure out how to stop board side crossing a cleaner way
    // do not make squares which cross over the board available
    private static void wallKnight() {

        if ((selectedSquare == 0) || (selectedSquare == 1) || (selectedSquare == 8) || (selectedSquare == 9) || (selectedSquare == 16) || (selectedSquare == 17) || (selectedSquare == 24) || (selectedSquare == 25) || (selectedSquare == 32) || (selectedSquare == 33) || (selectedSquare == 40) || (selectedSquare == 41) || (selectedSquare == 48) || (selectedSquare == 49) || (selectedSquare == 56) || (selectedSquare == 57))

        {
            GameActivity.h1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.h1.setOnClickListener(null);
            GameActivity.g1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.g1.setOnClickListener(null);
            GameActivity.h2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.h2.setOnClickListener(null);
            GameActivity.g2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.g2.setOnClickListener(null);
            GameActivity.h3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.h3.setOnClickListener(null);
            GameActivity.g3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.g3.setOnClickListener(null);
            GameActivity.h4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.h4.setOnClickListener(null);
            GameActivity.g4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.g4.setOnClickListener(null);
            GameActivity.h5.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.h5.setOnClickListener(null);
            GameActivity.g5.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.g5.setOnClickListener(null);
            GameActivity.h6.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.h6.setOnClickListener(null);
            GameActivity.g6.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.g6.setOnClickListener(null);
            GameActivity.h7.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.h7.setOnClickListener(null);
            GameActivity.g7.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.g7.setOnClickListener(null);
            GameActivity.h8.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            GameActivity.h8.setOnClickListener(null);
            GameActivity.g8.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            g8.setOnClickListener(null);
        } else if ((selectedSquare == 6) || (selectedSquare == 7) || (selectedSquare == 14) || (selectedSquare == 15) || (selectedSquare == 22) || (selectedSquare == 23) || (selectedSquare == 30) || (selectedSquare == 31) || (selectedSquare == 38) || (selectedSquare == 39) || (selectedSquare == 46) || (selectedSquare == 47) || (selectedSquare == 54) || (selectedSquare == 55) || (selectedSquare == 62) || (selectedSquare == 63))

        {
            a1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a1.setOnClickListener(null);
            b1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b1.setOnClickListener(null);
            a2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a2.setOnClickListener(null);
            b2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b2.setOnClickListener(null);
            a3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a3.setOnClickListener(null);
            b3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b3.setOnClickListener(null);
            a4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a4.setOnClickListener(null);
            b4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b4.setOnClickListener(null);
            a5.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a5.setOnClickListener(null);
            b5.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b5.setOnClickListener(null);
            a6.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a6.setOnClickListener(null);
            b6.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b6.setOnClickListener(null);
            a7.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a7.setOnClickListener(null);
            b7.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b7.setOnClickListener(null);
            a8.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            a8.setOnClickListener(null);
            b8.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            b8.setOnClickListener(null);


        }

    }

    // if space in row directly ahead or behind not in same column as rook, make unavailable
    //TODO: consider more elegant solution to walling off rooks at sides
    private static void wallPawn(int i) {
        if ((selectedSquare == 15) || (selectedSquare == 23) || (selectedSquare == 31) || (selectedSquare == 39) || (selectedSquare == 47) || (selectedSquare == 55)) {
            if ((i == selectedSquare + 9)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }

        if ((selectedSquare == 48) || (selectedSquare == 40) || (selectedSquare == 32) || (selectedSquare == 24) || (selectedSquare == 16) || (selectedSquare == 8)) {
            if ((i == selectedSquare - 9)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }

        if ((selectedSquare == 15) || (selectedSquare == 23) || (selectedSquare == 31) || (selectedSquare == 39) || (selectedSquare == 47) || (selectedSquare == 55)) {
            if ((i == selectedSquare - 7)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }

        if ((selectedSquare == 48) || (selectedSquare == 40) || (selectedSquare == 32) || (selectedSquare == 24) || (selectedSquare == 16) || (selectedSquare == 8)) {
            if ((i == selectedSquare + 7)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }
    }

    // if space in row directly ahead or behind not in same column as rook, make unavailable
    //TODO: consider more elegant solution to walling off rooks at sides
    private static void wallRook(int i) {

        if (selectedSquare <= 7 && ((i == 8) || (i == 9) || (i == 10) || (i == 11) || (i == 12) || (i == 13) || (i == 14) || (i == 15))) {
            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }

        }

        if ((selectedSquare >= 8 && selectedSquare <= 15) && ((i == 0) || (i == 1) || (i == 2) || (i == 3) || (i == 4) || (i == 5) || (i == 6) || (i == 7) || (i == 16) || (i == 17) || (i == 18) || (i == 19) || (i == 20) || (i == 21) || (i == 22) || (i == 23))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }


        if ((selectedSquare >= 16 && selectedSquare <= 23) && ((i == 8) || (i == 9) || (i == 10) || (i == 11) || (i == 12) || (i == 13) || (i == 14) || (i == 15) || (i == 24) || (i == 25) || (i == 26) || (i == 27) || (i == 28) || (i == 29) || (i == 30) || (i == 31))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }


        if ((selectedSquare >= 24 && selectedSquare <= 31) && ((i == 16) || (i == 17) || (i == 18) || (i == 19) || (i == 20) || (i == 21) || (i == 22) || (i == 23) || (i == 32) || (i == 33) || (i == 34) || (i == 35) || (i == 36) || (i == 37) || (i == 38) || (i == 39))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }


        if ((selectedSquare >= 32 && selectedSquare <= 39) && ((i == 24) || (i == 25) || (i == 26) || (i == 27) || (i == 28) || (i == 29) || (i == 30) || (i == 31) || (i == 40) || (i == 41) || (i == 42) || (i == 43) || (i == 44) || (i == 45) || (i == 46) || (i == 47))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }


        if ((selectedSquare >= 40 && selectedSquare <= 47) && ((i == 32) || (i == 33 || (i == 34) || (i == 35) || (i == 36) || (i == 37) || (i == 38) || (i == 39) || (i == 48) || (i == 49) || (i == 50) || (i == 51) || (i == 52) || (i == 53)) || (i == 54) || (i == 55))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }

        if ((selectedSquare >= 48 && selectedSquare <= 56) && ((i == 40) || (i == 41) || (i == 42) || (i == 43) || (i == 44) || (i == 45) || (i == 46) || (i == 47) || (i == 56) || (i == 57) || (i == 58) || (i == 59) || (i == 60) || (i == 61) || (i == 62) || (i == 63))) {

            if (i != selectedSquare + 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }

        if ((selectedSquare >= 56 && selectedSquare <= 63) && ((i == 48) || (i == 49) || (i == 50) || (i == 51) || (i == 52) || (i == 53) || (i == 54) || (i == 55))) {
            if (i != selectedSquare - 8) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }

    }

    // if space is not the same color as that stood on by the bishop
    // make unavailable
    private static void wallBishop(int i) {
        if (isWhite(selectedSquare)) {
            if (isBlack(i)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }
        if (isBlack(selectedSquare)) {
            if (isWhite(i)) {
                ImageView nulled = getSquareImageView(i);
                nulled.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                nulled.setOnClickListener(null);
            }
        }

    }


    // Check if a square is free
    private static Boolean isFree(int i) {
        return (gamesetList.get(i).equals("free_square"));
    }


    // Check if square is white
    private static Boolean isWhite(int i) {
        return ((i == 1) || (i == 3) || (i == 5) || (i == 7) || (i == 8) || (i == 10) || (i == 12) || (i == 14) || (i == 17) || (i == 19) || (i == 21) || (i == 23) || (i == 24) || (i == 26) || (i == 28) || (i == 30) || (i == 33) || (i == 35) || (i == 37) || (i == 39) || (i == 40) || (i == 42) || (i == 44) || (i == 46) || (i == 49) || (i == 51) || (i == 53) || (i == 55) || (i == 56) || (i == 58) || (i == 60) || (i == 62));
    }

    // Check if square is white
    private static Boolean isBlack(int i) {
        return ((i == 0) || (i == 2) || (i == 4) || (i == 6) || (i == 9) || (i == 11) || (i == 13) || (i == 15) || (i == 16) || (i == 18) || (i == 20) || (i == 22) || (i == 25) || (i == 27) || (i == 29) || (i == 31) || (i == 32) || (i == 34) || (i == 36) || (i == 38) || (i == 41) || (i == 43) || (i == 45) || (i == 47) || (i == 48) || (i == 50) || (i == 52) || (i == 54) || (i == 57) || (i == 59) || (i == 61) || (i == 63));
    }

    // Check if a square is either held by player black or free
    private static Boolean heldBlackOrFree(int i) {
        return ((gamesetList.get(i).equals("free_square")) || (gamesetList.get(i).equals("black_pawn")) || (gamesetList.get(i).equals("black_king")) || (gamesetList.get(i).equals("black_queen")) || (gamesetList.get(i).equals("black_bishop")) || (gamesetList.get(i).equals("black_knight")) || (gamesetList.get(i).equals("black_rook")));
    }

    // Check if a square is either held by player white or free
    private static Boolean heldWhiteOrFree(int i) {
        return ((gamesetList.get(i).equals("free_square")) || (gamesetList.get(i).equals("white_pawn")) || (gamesetList.get(i).equals("white_king")) || (gamesetList.get(i).equals("white_queen")) || (gamesetList.get(i).equals("white_bishop")) || (gamesetList.get(i).equals("white_knight")) || (gamesetList.get(i).equals("white_rook")));
    }

    // to conveniently get ImageView from the int value of the square
    // TODO: there are definitely more clever/efficient ways to do this
    private static ImageView getSquareImageView(int square) {
        ImageView imgView = null;
        switch (square) {
            case 0:
                imgView = a1;
                break;
            case 1:
                imgView = b1;
                break;
            case 2:
                imgView = c1;
                break;
            case 3:
                imgView = d1;
                break;
            case 4:
                imgView = e1;
                break;
            case 5:
                imgView = f1;
                break;
            case 6:
                imgView = g1;
                break;
            case 7:
                imgView = h1;
                break;

            case 8:
                imgView = a2;
                break;
            case 9:
                imgView = b2;
                break;
            case 10:
                imgView = c2;
                break;
            case 11:
                imgView = d2;
                break;
            case 12:
                imgView = e2;
                break;
            case 13:
                imgView = f2;
                break;
            case 14:
                imgView = g2;
                break;
            case 15:
                imgView = h2;
                break;


            case 16:
                imgView = a3;
                break;
            case 17:
                imgView = b3;
                break;
            case 18:
                imgView = c3;
                break;
            case 19:
                imgView = d3;
                break;
            case 20:
                imgView = e3;
                break;
            case 21:
                imgView = f3;
                break;
            case 22:
                imgView = g3;
                break;
            case 23:
                imgView = h3;
                break;

            case 24:
                imgView = a4;
                break;
            case 25:
                imgView = b4;
                break;
            case 26:
                imgView = c4;
                break;
            case 27:
                imgView = d4;
                break;
            case 28:
                imgView = e4;
                break;
            case 29:
                imgView = f4;
                break;
            case 30:
                imgView = g4;
                break;
            case 31:
                imgView = h4;
                break;

            case 32:
                imgView = a5;
                break;
            case 33:
                imgView = b5;
                break;
            case 34:
                imgView = c5;
                break;
            case 35:
                imgView = d5;
                break;
            case 36:
                imgView = e5;
                break;
            case 37:
                imgView = f5;
                break;
            case 38:
                imgView = g5;
                break;
            case 39:
                imgView = h5;
                break;


            case 40:
                imgView = a6;
                break;
            case 41:
                imgView = b6;
                break;
            case 42:
                imgView = c6;
                break;
            case 43:
                imgView = d6;
                break;
            case 44:
                imgView = e6;
                break;
            case 45:
                imgView = f6;
                break;
            case 46:
                imgView = g6;
                break;
            case 47:
                imgView = h6;
                break;

            case 48:
                imgView = a7;
                break;
            case 49:
                imgView = b7;
                break;
            case 50:
                imgView = c7;
                break;
            case 51:
                imgView = d7;
                break;
            case 52:
                imgView = e7;
                break;
            case 53:
                imgView = f7;
                break;
            case 54:
                imgView = g7;
                break;
            case 55:
                imgView = h7;
                break;

            case 56:
                imgView = a8;
                break;
            case 57:
                imgView = b8;
                break;
            case 58:
                imgView = c8;
                break;
            case 59:
                imgView = d8;
                break;
            case 60:
                imgView = e8;
                break;
            case 61:
                imgView = f8;
                break;
            case 62:
                imgView = g8;
                break;
            case 63:
                imgView = h8;
                break;

        }


        return imgView;


    }


    // TODO: make uniform -- half of the above have "GameActivity.moveGamepiece", while the rest refer to this function
    private static void moveGamepiece(int localI) {
        GameActivity.moveGamepiece(localI);
    }


}
