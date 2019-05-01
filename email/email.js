const express = require("express")
const cors = require("cors")
const bodyParser = require("body-parser")
const nodeMailer = require("nodemailer")
const details = require("./details.json")
const port = 3000

const app = express();
app.use(cors({origin: "*"}))
app.use(bodyParser.json())

app.listen(port, () => {
  console.log("The server start on port " + port)
})

app.get("/", (request, response) => {
  response.send(
    "<h3>Server is runnning. Send an email</h3>"
  )
})

app.post("/sendEmail", (request, response) => {
  console.log("REQUEST CAME")
  const user = request.body;
  console.log(user)
  sendMail(user, information => {
    console.log("EMAIL SENT")
    response.send(information)
  })
})

async function sendMail(user, callback) {
  let transporter = nodeMailer.createTransport({
    host: "smtp.gmail.com",
    port: 587,
    secure: false,
    auth: {
      user: details.email,
      pass: details.password
    }
  });

  let mailOptions = {
    from: details.email,
    to: user.email,
    subject: "Account Discrepency",
    html: `<h3>Plan: ${user.account}</h3><br>
    <p>Plan ${user.account} has an account name
    that does not match the plan number. Please
    update the account name to match the plan
    number as soon as possible. Thank you.</p>`
  };

  let info = await transporter.sendMail(mailOptions);

  callback(info);
}
