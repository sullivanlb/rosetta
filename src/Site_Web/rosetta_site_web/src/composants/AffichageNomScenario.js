import React, { Component, Fragment } from "react";

/**
 * @description Ce composant représente l'affichage du nom du scénario en cours de création (Nouveau scénario).
 * 
 * @author Christophe GARCIA
 */
export default class AffichageNomScenario extends Component {
	render() {
		return (
			<Fragment>
				<h6 style={{"text-align":"center"}}>
						{this.props.nom}
				</h6>
			</Fragment>
		);
	}
}