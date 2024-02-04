import React, { Component } from "react";
import DepartmentService from "./services/department-service.js";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            departments: [],
        };
    }

    async componentDidMount() {
        try {
            const response = await DepartmentService.getDepartments();
            const departments = response.data;
            this.setState({ departments });
        } catch (error) {
            console.error('Error fetching departments:', error);
        }
    }

    updateLectorName = async (lectorId, newName) => {
        try {
            const updatedLector = await DepartmentService.updateLectorName(lectorId, newName);
            const { departments } = this.state;

            this.setState({
                departments: departments.map(department => ({
                    ...department,
                    lectors: department.lectors.map(lector =>
                        lector.id === lectorId ? { ...lector, name: updatedLector.name } : lector
                    ),
                })),
            });
        } catch (error) {
            console.error('Error updating lecturer name:', error);
        }
    };

    render() {
        const { departments } = this.state;

        return (
            <div>
                <h1>List of Departments</h1>
                {departments.map((department) => (
                    <div key={department.id} style={{ marginBottom: '20px', border: '1px solid #000', padding: '10px' }}>
                        <h2>{department.name}</h2>
                        <table>
                            <thead>
                            <tr>
                                <th>Lecturer Name</th>
                                <th>Degree</th>
                            </tr>
                            </thead>
                            <tbody>
                            {department.lectors.map((lecturer) => (
                                <tr key={lecturer.id}>
                                    <td>
                                        <input
                                            type="text"
                                            value={lecturer.name}
                                            onChange={(e) => this.updateLectorName(lecturer.id, e.target.value)}
                                        />
                                    </td>
                                    <td>{lecturer.degree}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                ))}
            </div>
        );
    }
}

export default App;
