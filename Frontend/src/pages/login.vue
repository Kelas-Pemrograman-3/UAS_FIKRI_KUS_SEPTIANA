<template>
  <q-page
  class="bg-indigo-10"
  >
    <div class="row justify-center q-pt-lg">
      <div class="col-md-4 col-xs-8">
        <q-card flat>
          <q-card-section>
            <div class="text-h4 q-pb-md text-center "><b>ADMIN LOGIN</b></div>
            <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-sm"
    >

      <q-input
        filled
        v-model="user"
        label="Masukan User *"
        lazy-rules
        :rules="[
          val => val !== null && val !== '' || 'Please type your User']"
      />

      <q-input
        filled
        type="Password"
        v-model="password"
        label="Masukan Password *"
        lazy-rules
        :rules="[ val => val !== '' && val !== null || 'Please type Your Password']"
      />

      <div>
        <q-btn label="Masuk" type="submit" color="primary"/>
        <q-btn label="Daftar" to="/register" color="primary" flat class="q-ml-sm" />
      </div>
    </q-form>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  data () {
    return {
      user: '',
      password: ''
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('admin/login', {
        user: this.user,
        password: this.password
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.showNotif(res.data.pesan, 'Positive')
          this.$router.push('/index')
        }
        this.onReset()
      })
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: color
      })
    }
  }
}
</script>
