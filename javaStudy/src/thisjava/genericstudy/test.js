/* eslint-disable */
import './App.css';
import {useState} from "react";
// import uuid, {v4} from "uuid";

// 인풋에 값을 입력하고 추가버튼을 누르면 로컬스토리지에 배열 형태로 저장
// 로컬스토리지에 저장된 값을 리스트로 출력
// 완료버튼을 누르면 css style 완료표시
// 삭제버튼을 누르면 리스트에서 삭제되면서 로컬스토리지 배열에서도 빠지기

function App() {

    const [todo, setTodo] = useState([]);

    //데아터를 JSON 형태로 로컬스토리지에 저장
    const saveTodo = (todo) => {
        const list = JSON.parse(localStorage.getItem("list")) || [];
        list.push(todo);
        localStorage.setItem("list", JSON.stringify(todo));
    }

    // 로컬스토리지에 있는 데이터를 가져오기
    let getData = JSON.parse(localStorage.getItem("list"));

    return (
        <div className="App">
            <div className="container">
                <h1>ToDoList💜</h1>

                <div className="input-list">
                    <input className="todo" placeholder="할일을 입력하세용"
                           onChange={(e) => {
                               setTodo(e.target.value)
                           }}></input>
                    <button type="button" className="btn btn-outline-primary"
                            onClick={() => {
                                saveTodo(todo);
                            }}
                    >추가
                    </button>
                </div>

                <table className="table">
                    <thead>
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">ToDo</th>
                        <th scope="col">Done</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>

                    <tbody>
                    {/*{getData?.map((item, index) => {*/}
                    {/*    return (<tr>*/}
                    {/*      <th scope="row">{index+1}</th>*/}
                    {/*      <td>{item.value}</td>*/}
                    {/*      <td>*/}
                    {/*        <button type="button" className="btn btn-success">완료</button>*/}
                    {/*      </td>*/}
                    {/*      <td>*/}
                    {/*        <button type="button" className="btn btn-outline-danger"*/}
                    {/*                // onClick={() => {*/}
                    {/*                //   localStorage.removeItem(item.key);*/}
                    {/*                // }}*/}
                    {/*        >삭제*/}
                    {/*        </button>*/}
                    {/*      </td>*/}
                    {/*    </tr>*/}
                    {/*    )}*/}
                    {/*)}*/}
                    </tbody>
                </table>

            </div>
        </div>
    );
}

export default App;
