import axios from 'axios';

const API_URL = 'http://localhost:8080';

class DepartmentService {
    getDepartments(){
        return axios.get(`${API_URL}/departments`)
    }

    updateLectorName(lectorId, newName){
        return axios.put(`${API_URL}/update-name`, null, {
            params: {
                lectorId: lectorId,
                newName: newName,
            }
        }).then(response => {
            console.log(response.data);
            return response.data;
        })
            .catch(error => {
                console.error(error);
                throw error;
            });
    }
}

export default new DepartmentService();
