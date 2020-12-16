import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

//WingTime said to call it GUN INFERNO 69

public class Gameplay extends PApplet {
	//gameState
	private int gameState = 0;
	/*
	 * 0 - menu
	 * 1 - game
	 * 2 - next battle
	 * 3 - end
	 */
	private int inGameState = 0;
	/*
	 * 0 - spawning enemy
	 * 1 - battle
	 */
	//arraylist of bullets
	private ArrayList<Bullet> bullets;
	private ArrayList<Bullet> enemyBull;
	//main figure variables
	private PlayerShip fig;
	//fig number type
	private int figNum = 1;
	private PImage redShip;
	private PImage blueShip;
	private PImage greenShip;
	private PImage whiteShip;
	
	//sfx	
	private Audio music;
	
	private boolean immunity = false;
	private int immuneCount;
	private PImage immunityCircle;
	//arraylist of enemy bullets
	private Bosses boss;
	
	//screen images
	private PImage backGround;
	private PImage startMenu;
	private PImage winScreen;
	private PImage loseScreen;
	private PImage heart;
	
	//score
	private int score;
	private boolean win = false;
	
	public static void main(String[] args) {
		PApplet.main(Gameplay.class.getName());
	}
	
    public void settings() {
    	size(720, 900);
	}
    
    public void setup() {
    	frameRate(60);
    	//ship images
    	redShip = loadImage("lib//ships//redShip2.png");
    	redShip.resize(400, 400);
    	blueShip = loadImage("lib//ships//blueShip2.png");
    	blueShip.resize(400, 400);
    	greenShip = loadImage("lib//ships//greenShip.png");
    	greenShip.resize(400, 400);
    	whiteShip = loadImage("lib//ships//whiteShip.png");
    	whiteShip.resize(400, 400);
    	
    	fig = new PlayerShipRed(this, width/2, 700);
    	
    	immunityCircle = loadImage("lib//circles//immunityCircle.png");
    	
    	bullets = new ArrayList<Bullet>();
    	enemyBull = new ArrayList<Bullet>();
    	
    	backGround = loadImage("lib//screen//backGround1.png");
    	startMenu = loadImage("lib//screen//startMenu.png");
    	winScreen = loadImage("lib//screen//winScreen.png");
    	loseScreen = loadImage("lib//screen//deathScreen.png");
    	
    	heart = loadImage("lib//heart.png");
    	
    	music = new Audio("lib//music//Tetris.wav");
    	music.loop();
	}
    
    public void draw() {
    	if(gameState == 0) {
    		menuScreen();
    	}
    	else if(gameState == 1) {
    		game();
    	}
    	else if(gameState == 2) {
    		winScreen();
    	}
    	else if(gameState == 3) {
    		loseScreen();    		
    	}
    }
     
    private void menuScreen() {
    	if(figNum > 4)
    		figNum = 1;
    	else if(figNum < 1)
    		figNum = 4;
    	if(figNum == 1) {
    		background(255, 0, 0);
    		image(startMenu, 0, 0);
    		image(redShip, width/2 - 200, 300);
    	}
    	else if(figNum == 2) {
        	background(0, 0, 155);
        	image(startMenu, 0, 0);
    		image(blueShip, width/2 - 200, 300);
    	}
    	else if(figNum == 3) {
        	background(0, 125, 0);
        	image(startMenu, 0, 0);
    		image(greenShip, width/2 - 200, 300);
    	}
    	else if(figNum == 4) {
        	background(100);
        	image(startMenu, 0, 0);
    		image(whiteShip, width/2 - 200, 300);
    	}
    	
    	fill(255);
    	textSize(20);
    	textAlign(CENTER);
    	text("PRESS ENTER TO BEGIN", width/2, 250);
    	if(enter) {
    		if(figNum == 1) {
    			fig = new PlayerShipRed(this, width/2, 700);
        	}
        	else if(figNum == 2) {
        		fig = new PlayerShipBlue(this, width/2, 700);
        	}
        	else if(figNum == 3) {
        		fig = new PlayerShipGreen(this, width/2, 700);
        	}
        	else if(figNum == 4) {
        		fig = new PlayerShipWhite(this, width/2, 700);
        	}
    		enter = false;
    		inGameState = 0;
    		gameState = 1;
    	}
    }

    private void game() {
    	//background(0);
    	for(int y = -(frameCount%height); y < height; y += height)
    		this.copy(backGround, 0, y, width, height, 0, 0, width, height);
    	
    	thread("move");
    	thread("playerBulletRun");
    	thread("enemyBulletRun");
    	/*
    	move();
    	playerBulletRun();
    	enemyBulletRun();
    	*/
    	if(inGameState == 0) {
    		//creates random boss
    		int rng = (int)(Math.random()*3) + 1;
    		//System.out.println(rng);
    		//int rng = 1;
    		if(rng == 1)
    			boss = new Boss1(this, width/2, -200, 2);
    		else if(rng == 2)
    			boss = new Boss2(this, width/2, -200, 2);
    		else if(rng == 3)
    			boss = new Boss3(this, width/2, -200, 2);
    		//incomplete boss
    		else if(rng == 4)
    			boss = new Boss4(this, width/2, - 200, 2);
        	inGameState = 1;
    	}
    	else if(inGameState == 1){
    		boss.show();
    		thread("runBoss");
    		//runBoss();
        }
    	    	
    	if(immunity) {
    		immunityCircle.resize(50 + immuneCount, 50 + immuneCount);
    		image(immunityCircle, fig.getPosX() - immunityCircle.width/2, fig.getPosY() - immunityCircle.height/2);
    	}
    	
    	//draw bullets from player controlled figure
    	for(int i = 0; i < bullets.size(); i++)
    		if(bullets.get(i) != null)
    			bullets.get(i).show();
    	
    	//draw player controlled figures
    	fig.show();
    	if(shift)
    		fig.drawOrbs();
		for(int i = 0; i < enemyBull.size(); i++)
			if(enemyBull.get(i) != null)
				enemyBull.get(i).show();
		
		//draw hearts based on player lives
		for(int i = 0; i < fig.lives(); i++) 
    		image(heart, 10 + 20*i, 10);
    }
   
    private void winScreen() {
    	if(win) {
    		score++;
    		win = false;
    	}
    	
    	image(winScreen, 0, 0);
    	fill(255);
    	textSize(50);
    	textAlign(CENTER, CENTER);
    	text("SCORE:", width/2 - 90, 100);
    	textSize(25);
    	text("ONE LIFE ADDED", width/2, 700);
    	image(heart, width/2 + 110, 693);
    	text("PRESS ENTER TO CONTINUE", width/2, 750);
    	textSize(200);
    	text(score, width/2-90, 200);
    	
    	if(enter) {
    		fig.addLife();
    		gameState = 1;
    		reset();
    	}
    }
    
    private void loseScreen() {
    	image(loseScreen, 0, 0);
    	fill(255);
    	textSize(50);
    	text("SCORE:", width/2 + 40, 100);
    	textAlign(CENTER, CENTER);
    	textSize(25);
    	text("PRESS ENTER TO RETURN TO MENU", width/2, 800);
    	textSize(200);
    	text(score, width/2+200, 100);
    	
    	if(enter) {
    		score = 0;
    		gameState = 0;
    		reset();
    	}
    }
    
    //resets keypress and arraylists
    private void reset() {
    	bullets.clear();
		enemyBull.clear();
		enter = false;
		inGameState = 0;
		figNum = 1;
    	up = false;
		down = false;
		left = false;
		right = false;
		shift = false;
		space = false;
    }
    
    //key press while in gameplay
    private boolean up = false;
    private boolean left = false;
    private boolean right = false;
    private boolean down = false;
    private boolean shift = false;
    private boolean space = false;
    private boolean enter = false;
    
    //key press method
    public void keyPressed() {
    	if(gameState == 1) {
    		if(key == ' ')
    			space = !space;
    		if(keyCode == SHIFT)
    			shift = true;
    		if(keyCode == UP)
    			up = true;
    		if(keyCode == DOWN)
    			down = true;
    		if(keyCode == LEFT)
    			left = true;
    		if(keyCode == RIGHT)
    			right = true;
    	}
    }
    public void keyReleased() {
    	if(gameState != 1) {
    		if(keyCode == RIGHT)
    			figNum++;
    		if(keyCode == LEFT)
    			figNum--;
    		if(keyCode == ENTER)
    			enter = true;
    	}
    	//if(key == ' ')
	    //	space = false;
    	if(gameState == 1) {
    		if(keyCode == SHIFT)
    			shift = false;
    		if(keyCode == UP)
    			up = false;
    		if(keyCode == DOWN)
    			down = false;
    		if(keyCode == LEFT)
    			left = false;
    		if(keyCode == RIGHT)
    			right = false;
    	}
    }
    
    public void runBoss() {
    	boss.move();
    	boss.shoot(frameCount, fig, enemyBull);
    }
    
    //enemy bullet collision and movement
    public void enemyBulletRun() {
    	for(int i = 0; i < enemyBull.size(); i++) {
   			enemyBull.get(i).move();
   			if(!enemyBull.get(i).checkBound()) {
   				enemyBull.remove(i);
   				i--;
   			}
   		}
   		if(!immunity) {
   			for(int i = 0; i < enemyBull.size(); i++) {
				if(dist(enemyBull.get(i).getPosX(), enemyBull.get(i).getPosY(), fig.getPosX(), fig.getPosY())
						< enemyBull.get(i).getSize()/2 + 1) {
					enemyBull.remove(i);
					if(!fig.die()) {
						gameState = 3;
					}
   					else {
   						immunity = true;
   						immuneCount = 90;
   						break;
   					}
   				}
   			}
   		}
   		else {
   			immuneCount--;
   			if(immuneCount == 0) {
  				immunityCircle = loadImage("lib//circles//immunityCircle.png");
   				immunity = false;
  			}
   		}
   	}

    
    
    //checks Shoot for playerBullets
    public void playerBulletRun() {
    	//bullet collision with boss
   		for(int i = 0; i < bullets.size(); i++) {
   			if(bullets.get(i) != null) {
   				bullets.get(i).move();
    			if(!bullets.get(i).checkBound()) {
    				bullets.remove(i);
    				i--;
    			}
    			else if(dist(bullets.get(i).getPosX(), bullets.get(i).getPosY(), boss.getPosX(), boss.getPosY())
   						< bullets.get(i).getSize() + boss.hitBoxSize) {
   					if(inGameState == 1) {
   						if(!boss.hit(bullets.get(i).getType())) {
   							win = true;
   							gameState = 2;
   						}
   					}
   					bullets.remove(i);
   					i--;
   				}
   			}
   		}
    }
    
    //controls player movement
    public void move() {
    	//speedChange and change spread of bullet
    	if(shift) {
    		fig.changeLowSpeed();
    		fig.lowChange();
    	}
    	else {
    		fig.normChange();
    		fig.changeNormSpeed();
    	}
    	//moves
    	if(up)
    		if(fig.getPosY() > 0)
    			fig.setPosY(fig.getPosY() - fig.getSpeed());
    	if(down)
    		if(fig.getPosY() < (height))
    			fig.setPosY(fig.getPosY() + fig.getSpeed());
    	if(left)
    		if(fig.getPosX() > 0)
    			fig.setPosX(fig.getPosX() - fig.getSpeed());
    	if(right)
    		if(fig.getPosX() < (width))
    			fig.setPosX(fig.getPosX() + fig.getSpeed());
    	//shoot(toggles)
    	if(space)
    		fig.shoot(frameCount, boss, bullets);
    }     
}