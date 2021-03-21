import React, { Component, Fragment } from "react";

/**
 * @description Ce composant représente l'affichage des questions du scénario en cours de création (Nouveau scénario).
 * 
 * @author Christophe GARCIA
 */
export default class AffichageQuestions extends Component {
	render() {
		return (
			<Fragment>
				{this.props.scenario.questions.map((question) => {
					return <ul className="list-group">
						<li className="list-group-item">{question.nom}</li>
					</ul>
				})}
			</Fragment>
		);
	}
}