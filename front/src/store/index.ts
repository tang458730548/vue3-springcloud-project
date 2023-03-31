import {createStore} from 'vuex'

declare let SessionStorage: any;
const USER = "USER"

export default createStore({
    state: {
        user: SessionStorage.get(USER) || {}
    },
    mutations: {
        setUser(state, user) {
            console.log(user)
            state.user = user
            SessionStorage.set(USER, user)
        }
    },
    actions: {},
    modules: {}
})
