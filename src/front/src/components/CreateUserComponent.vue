<template>
  <form class="mx-auto max-w-xs">
    <div class="flex flex-wrap -mx-3 mb-6">
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="username">
          Username
        </label>
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="username" type="text" placeholder="Username" v-model="username">
        <p class="text-red-500 text-xs italic">Please fill out this field.</p>
      </div>
    </div>
    <div class="flex flex-wrap -mx-3 mb-6">
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="password">
          Password
        </label>
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="password" type="password" placeholder="Password" v-model="password">
        <p class="text-gray-600 text-xs italic">Must use utf-8 character</p>
      </div>
    </div>
      <div class="mb-9">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="confirm">
          Confirm your password
        </label>
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="confirm" type="password" placeholder="Confirm your password"  v-model="confirm" required>
      </div>
    <div class="flex items-center justify-between">
      <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:shadow-outline" type="button" @click="register();">
        Register
      </button>
      <a class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800">
        <router-link to="/connexion">Already have an account</router-link>
      </a>
    </div>
    <div v-if="booleanPassword" class="pt-1.5 text-red-500">Not matching password</div>
    <div v-if="booleanUsername" class="pt-1.5 text-red-500">Username already taken</div>
  </form>
</template>

<script>
import router from "@/router";

export default {

  name: "CreateUserComponent",
  data: () => ({
    username: '',
    password: '',
    confirm: '',
    booleanUsername: false,
    booleanPassword: false,
  }),

  methods: {

    async alreadyRegistered() {
      await fetch("/user/exist/" + this.username,
          {
            method: 'GET',
            headers: {"Content-Type": "application/json"},
          }).then((res => {
        this.booleanUsername = res.status === 200
      }))
    },


    async register() {
      this.booleanUsername = false
      if (this.password === this.confirm) {
        await this.alreadyRegistered()
        const resp = await fetch("/user/save",
            {
              method: 'POST',
              headers: {"Content-Type": "application/json"},
              body: JSON.stringify({username: this.username, password: this.password})
            }
        )
        if (resp.status === 201 && !this.booleanUsername) {
          await router.push("Connexion")
        }
      } else {
        this.booleanPassword = true
      }
    }
  }
}
</script>
