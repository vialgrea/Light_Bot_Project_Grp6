package observable.action;
import observable.couleur.Couleur;
import observable.map.*;
import observable.robot.Robot;
import exception.MouvementEx;

public class Activate implements int_Action{
	private Couleur color;
	public static Activate activate(){
		return new Activate();
	}
	public static Activate activate(Couleur color){
		return new Activate(color);
	}
	private Activate(){
		this.color = Couleur.GRIS;
	}
	private Activate(Couleur col){
		this.color = col;
	}
	@Override
	public void execute(Robot r) throws MouvementEx {
	abstr_Case cprime = r.getCurrent_Case();
	if (isPossible(r,cprime)){
		if(cprime.getClass().getCanonicalName().equals("map.Teleporter_Case")){
			r.setCurrent_Case(((Teleporter_Case)cprime).get_destination());
		}
		else {
			r.set_couleur(cprime.get_couleur());
		}
	}
	else{
		throw (new MouvementEx("impossible à utiliser"));
		}
	}

	@Override
	public boolean isPossible(Robot r, abstr_Case c) {
		return (((c.getClass().getCanonicalName().equals("map.Teleporter_Case")&&
					(color.equals(r.get_couleur())||
						color.equals(Couleur.GRIS))) 
				|| c.getClass().getCanonicalName().equals("map.Painted_Case")));
	}
}
