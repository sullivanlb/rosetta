import { BrowserRouter, Switch, Route } from 'react-router-dom';
import '../node_modules/@fortawesome/fontawesome-free/css/all.min.css';
import "../node_modules/bootstrap-css-only/css/bootstrap.min.css";
import "../node_modules/mdbreact/dist/css/mdb.css";
import "./style/App.css";

import Accueil from './pages/Accueil';
import Devis from './pages/Devis';
import Client from './pages/Client';
import Scenario from './pages/Scenario';
import FooterPage from './composants/Footer';
import Navigation from './composants/Navigation';

function App() {
  return (
    <div>
      <BrowserRouter>
      
      <Navigation/>
      
        <Switch>
         <Route exact path="/" component={Accueil}/>
         <Route exact path="/client" component={Client}/>
         <Route exact path="/devis" component={Devis}/>
         <Route exact path="/scenario" component={Scenario}/>
        </Switch>
      </BrowserRouter>

      <footer className="footer fixed-bottom">
        <FooterPage />
      </footer>
    </div>
  );
}

export default App;
