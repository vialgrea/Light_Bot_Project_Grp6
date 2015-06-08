package action;

import couleur.Couleur;
import exception.MouvementEx;
import map.*;
import robot.Robot;

public class LightCase implements int_Action{
	
	Couleur color;
	public static LightCase light_case(){
		return new LightCase();
	}
	public static LightCase light_case(Couleur col){
		return new LightCase(col);
	}
	private LightCase(){
		this.color = Couleur.GRIS;
	}
	private LightCase(Couleur col){
		this.color = col;
	}
	
	
	public void execute(Robot r) throws MouvementEx {
		Illuminated_Case Case;
		if(isPossible(r,r.getCurrent_Case())){
				Case = (Illuminated_Case)r.getCurrent_Case();
				Case.set_active(!Case.get_active());
		}
		else{
			throw (new MouvementEx("impossible d'allumer cette case"));
		}
		
	}

	public boolean isPossible(Robot r, abstr_Case c) {
		return ((c.getClass().getCanonicalName() == "map.Illuminated_Case") 
				&& (color ==Couleur.GRIS || color==r.get_couleur()));
	}

}
