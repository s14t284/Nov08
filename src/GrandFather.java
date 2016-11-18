import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Point;
import java.util.Random;

/**
 * 敵キャラ：じいさん
 * @author riked
 *
 */
public class GrandFather extends Sprite{

	private Random rnd;
	private AudioClip sound;

	private double vx,vy;

	public GrandFather(double x, double y, String fileName, Map map, MainPanel mainpanel) {
		super(x,y,fileName,map,mainpanel);
		score = 50;
		rnd = new Random();

		//ぶつかったときの効果音
		sound = Applet.newAudioClip(getClass().getResource("music/break.wav"));;

	}

	@Override
	public void Update() {
		if(rnd.nextInt(2) % 2 == 0){
			vx = Map.TILE_SIZE / 32;
		} 
		else {
			vx = -1 * Map.TILE_SIZE / 32;
		}

		//重力で下向きに加速度がかかる
		vy += Map.GRAVITY;
		Point tile;

		tile = map.GetTileCllision(this, x+vx, y);
		//衝突するタイルがないので
		if(tile == null){
			x += vx; 
			if(vx > 0){
				dir = LEFT;
			}
			else{
				dir = RIGHT;
			}
		} else {
			//衝突するタイルがあるのでブロックにめり込まないように位置調整
			x = Map.TilesToPixels(tile.x) - width;
		}

		//y方向のあたり判定	
		double newY = y + vy;
		//移動先座標で衝突するタイルの位置を取得
		tile = map.GetTileCllision(this, x, newY);
		//衝突するタイルがないので
		if(tile == null){
			y = newY;//移動
		} else {
			//衝突するタイルがあるので
			if(vy > 0){ //下へ移動中
				//位置調整
				y = Map.TilesToPixels(tile.y) - height;
				vy = 0; //着地したのでy方向速度を0へ
			} else if(vy < 0){ //天井へぶつかったので
				//上へ移動中
				y = Map.TilesToPixels(tile.y + 1);
				vy = 0;
			}
		}
	}
	
	/**
	 * サウンドの再生
	 */
	public void play(){
		sound.play();
	}

}
