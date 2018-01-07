package Testobjekte;

import Release.IPetriNamedElements;

public class PetriNode {
	IPetriNamedElements element;
	PetriNode vater;
	PetriNode linkerSohn;
	PetriNode rechterBruder;
	
	public PetriNode(IPetriNamedElements element) {
		super();
		this.element = element;
	}

	public IPetriNamedElements getElement() {
		return element;
	}

	public void setElement(IPetriNamedElements element) {
		this.element = element;
	}

	public PetriNode getVater() {
		return vater;
	}

	public void setVater(PetriNode vater) {
		this.vater = vater;
	}

	public PetriNode getLinkerSohn() {
		return linkerSohn;
	}

	public void setLinkerSohn(PetriNode linkerSohn) {
		this.linkerSohn = linkerSohn;
	}

	public PetriNode getRechterBruder() {
		return rechterBruder;
	}

	public void setRechterBruder(PetriNode rechterBruder) {
		this.rechterBruder = rechterBruder;
	}


	
}
