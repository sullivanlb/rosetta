import { BrowserRouter, Switch, Route } from "react-router-dom";
import { Fragment } from "react";
import "../node_modules/@fortawesome/fontawesome-free/css/all.min.css";
import "../node_modules/bootstrap-css-only/css/bootstrap.min.css";
import "../node_modules/mdbreact/dist/css/mdb.css";
import "./style/App.css";

import Accueil from "./pages/Accueil";
import Devis from "./pages/Devis";
import NouveauDevis from "./pages/NouveauDevis";
import ModifierDevis from "./pages/ModifierDevis";
import Client from "./pages/Client";
import Scenario from "./pages/Scenario";
import NouveauScenario from "./pages/NouveauScenario";
import ComposantsPacks from "./pages/ComposantsPacks";
import NouveauClient from "./pages/NouveauClient";
import ModifierClient from "./pages/ModifierClient";
import FooterPage from "./composants/Footer";
import Navigation from "./composants/Navigation";

function App() {
  return (
    <Fragment>
      <BrowserRouter>
        <Navigation />

        {/*<NavbarPage/>
        **/}

        <Switch>
          <Route exact path="/" component={Accueil} />
          <Route exact path="/client" component={Client} />
          <Route exact path="/client/create" component={NouveauClient} />
          <Route exact path="/client/modify" component={ModifierClient} />
          <Route exact path="/devis" component={Devis} />
          <Route exact path="/devis/nouveau" component={NouveauDevis}/>
          <Route exact path="/devis/modifier" component={ModifierDevis}/>
          <Route exact path="/scenario" component={Scenario} />  
          <Route exact path="/scenario/nouveauscenario" component={NouveauScenario} />
          <Route exact path="/scenario/composantspacks" component={ComposantsPacks} />
        </Switch>
      </BrowserRouter>

      <footer className="footer">
        <FooterPage />
      </footer>
    </Fragment>
  );
}

export default App;
