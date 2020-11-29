import React,{Component} from 'react';
import './App.css';

class App extends Component{

	socket;

	constructor(props){
		
		super(props);
		this.state = {
			pets : [
				{animal: "Cat", number:0},
				{animal: "Dog", number:0},
			]
		}
		
		let socket = new WebSocket("ws://localhost:3000/results-api/myHandler");
		window.a = socket;

		socket.onopen = function(e) {
			socket.send("");
		  };
		  
		socket.onmessage = (event) => {
			let animalVotes = JSON.parse(event.data);
			this.setState({pets: animalVotes})
		};
		
	}


	vote (pet) {
    fetch("/voting-api/votingApi/" + pet)
	}

	render(){
		return(
			<>
			<div className="vh-100">
				<div className="d-flex justify-content-center">
					<div className="card mt-5" style={{width: "50%"}}>
						<div class="card-header">
							<h5>Voting and Results</h5>
						</div>
						<div className="list-group list-group-flush">
							{
								this.state.pets.map((pet, i) =>
									<div className="list-group-item" key={i}>
										<div className="row">
											<p className="col-sm-4">
												{pet.number}
											</p>
											<p className="col-sm-4">
												{pet.animal}
											</p>
											<button className="btn btn-primary col-sm-4" onClick={()=>this.vote(pet.animal)}>Click Here</button>
										</div>
										
									</div>
								)
							}
						</div>
					</div>
				</div>
			</div>
			
			
			</>
		);
	}
}
export default App;
