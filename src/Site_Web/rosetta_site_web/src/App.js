import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Accueil from './pages/Accueil';
import Devis from './pages/Devis';
import Client from './pages/Client';
import Scenario from './pages/Scenario';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Switch>
         <Route exact path="/" component={Accueil}/>
         <Route exact path="/client" component={Client}/>
         <Route exact path="/devis" component={Devis}/>
         <Route exact path="/scenario" component={Scenario}/>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
