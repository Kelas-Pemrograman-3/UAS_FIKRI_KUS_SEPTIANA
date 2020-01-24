<template>
  <q-page>
<div
class="judul text-h6 q-pa-md bg-grey-2 q-mb-md">
        <q-toolbar-title >
          Please input the lecturer data
        </q-toolbar-title>
<div class="komentar absolute-top-right q-pt-md q-pr-md">
<q-icon name="person" />
  Lecturer
</div>
</div>
    <div class="row justify-center q-pt-md">
      <div class="col-md-5 col-xs-8">
        <q-card>
          <q-card-section>
            <div class="text-h5 q-pb-sm text-center">
              <b>INPUT LECTURER</b>
            </div>
            <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-sm">

              <q-input
                filled
                v-model="nidn"
                label="NIDN *"
                mask="########"
                lazy-rules
                :rules="[ val => val !== null && val !== '' || 'Please type Your NIDN']"
              />

              <q-input
                filled
                type="password"
                v-model="password"
                label="Password *"
                lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type Your Password']"
              />

              <q-input
                filled
                v-model="namadosen"
                label="Full Name *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Name']"
              />

              <q-input v-model="tgllahir" filled type="date" hint="Please Click Your Birthdate"
              :rules="[ val => val !== '' && val !== null || 'Please Click Your Birthdate']"
              />

                <q-select
                class="q-pb-lg"
                filled v-model="jeniskelamin" :options="listjeniskelamin" label="Gender *"
                :rules="[ val => val !== '' && val !== null || 'Please type Your Gender']"
                />

                <div class="q-gutter-y-md q-pt-sm column" >
                <q-input filled v-model="email" type="email"
                :rules="[ val => val !== '' && val !== null || 'Please type Your Email']"
                label="Email"
                >
                  <template v-slot:before>
                    <q-icon name="mail" />
                  </template>
                </q-input>
                </div>

              <q-input
              filled
              v-model="notelepon"
              label="Phone Number"
              mask="(####) #########"
              lazy-rules
              :rules="[ val => val !== '' && val !== null || 'Please type Your Phone Number']"
              />

              <!-- <q-input
                filled
                v-model="kodemk"
                label="Course Code *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Course Code']"
              />

              <q-input
                filled
                v-model="namamk"
                label="Courses *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Courses']"
              /> -->

              <div>
                <q-btn label="Add +" type="submit" color="light-green-14" class="q-mt-md" />
                <q-btn label="Reset" type="reset" color="indigo-9" flat class="q-ml-sm q-mt-md" />
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
      nidn: '',
      password: '',
      namadosen: '',
      tgllahir: '',
      notelepon: '',
      jeniskelamin: '',
      email: '',
      listjeniskelamin: [
        'Male',
        'Female'
      ]
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('dosen/postdosen', {
        nidn: this.nidn,
        password: this.password,
        namadosen: this.namadosen,
        tgllahir: this.tgllahir,
        jeniskelamin: this.jeniskelamin,
        email: this.email,
        notelepon: this.notelepon
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.showNotif(res.data.pesan, 'Positive')
          this.onReset()
        }
      })
    },
    onReset () {
      this.nidn = ''
      this.password = ''
      this.namadosen = ''
      this.tgllahir = ''
      this.jeniskelamin = ''
      this.email = ''
      this.notelepon = ''
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
